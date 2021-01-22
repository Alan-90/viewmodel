package www.hbj.cloud.baselibrary.ngr_library.component.edittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.view.ViewCompat;

import java.lang.reflect.Field;

import www.hbj.cloud.baselibrary.R;
import www.hbj.cloud.baselibrary.ngr_library.utils.DensityUtil;
import www.hbj.cloud.baselibrary.ngr_library.utils.TextUtil;

/**
 * 自定义edittext，加入删除及显示剩余字数
 *
 * @author Administrator
 */
public class SysEditText extends AppCompatEditText {
    private static final String ELLIPSIS = "...";
    private Context mContext;
    private int maxLength;
    private Paint textPaintBlack;
    private Paint textPaintRed;
    private Drawable imgInable;
    private boolean bShowDel;
    private boolean bShowAllLength;
    private boolean bShowMaxLength = true;// default : show
    private int bReduceNumber = 50;//default: 50
    private float hintHeight;// count text padding
    private float hintWidth;// count text padding
    private View mEnableSwitchView;// View need to change enable state when text
    // count changes
    private float paddingBottomRight = 0f;// text position padding to layout
    private int mTextColor;
    private int mTextHintColor;
    // right

    public SysEditText(Context context) {
        super(context);
        mContext = context;
        init(null);
    }

    public SysEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        init(attrs);
    }

    public SysEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        // TODO Auto-generated constructor stub
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        TypedArray a = mContext.obtainStyledAttributes(attrs,
                R.styleable.sysEditText, 0, 0);
        bShowDel = a.getBoolean(R.styleable.sysEditText_delIcon, false);
        bShowMaxLength = a.getBoolean(R.styleable.sysEditText_showMaxLength, true);
        bReduceNumber = a.getInt(R.styleable.sysEditText_reduceNumber, 50);
        bShowAllLength = a.getBoolean(R.styleable.sysEditText_showAllLength, false);
        mTextColor = a.getColor(R.styleable.sysEditText_textColor,
                getResources().getColor(R.color.ngr_textColorSecondary));
        mTextHintColor = a.getColor(R.styleable.sysEditText_textHintColor,
                getResources().getColor(R.color.textColorHint));
        maxLength = getMaxLength();
        mInputFilter = getFilters();
        textPaintBlack = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaintBlack.setTextSize(getResources().getDimensionPixelSize(R.dimen.space_24));
        textPaintBlack.setColor(0xff959595);

        textPaintRed = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaintRed.setTextSize(getResources().getDimensionPixelSize(R.dimen.space_28));
        textPaintRed.setColor(0xffff0000);
        textPaintRed.setColor(0xff636363);

        int listItemHeight = (int) getResources().getDimension(R.dimen.list_item_height);
        this.setMinHeight(listItemHeight);
        this.setHintTextColor(mTextHintColor);
//        this.setTextColor(mTextColor);
//        hintHeight = 5 * DensityUtil.getDensity(mContext);
        hintHeight = 15 * DensityUtil.getDensity(mContext);
        hintWidth = 24 * DensityUtil.getDensity(mContext);
        imgInable = mContext.getResources().getDrawable(R.mipmap.icon_del_for_sysedit);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mEnableSwitchView != null) {
                    boolean enabled = s.toString().length() == 0 ? false : true;
                    mEnableSwitchView.setEnabled(enabled);
                }

                if (bShowDel && SysEditText.this.isFocused()) {
                    setDrawable();
                }
            }
        });
        if (bShowDel) {
            setDrawable();
        }

        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                } else {
                    if (bShowDel) {
                        setDrawable();
                    } else {
                        setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                    }
                }
            }
        });

    }

    public void showDelIcon() {
        if (bShowDel) {
            setDrawable();
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    /**
     * 设置是否需要显示删除按钮，默认不显示
     *
     * @param show 是否显示
     */
    public void setDelIconShow(boolean show) {
        bShowDel = show;
        if (!bShowDel) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        } else {
            setDrawable();
        }
    }

    InputFilter[] mInputFilter;

    /**
     * 设置最大字数限制
     *
     * @param length 长度
     */
    public void setMaxLength(int length) {
        maxLength = length;
        if (length >= 0) {
            mInputFilter = new InputFilter[]{new InputFilter.LengthFilter(length)};
            setFilters(mInputFilter);
            setEditableFactory(new Editable.Factory());
        } else {
            setFilters(new InputFilter[0]);
        }
    }

    StaticLayout mStaticLayout;

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        if (ViewCompat.isLaidOut(this)) {
            creatStaticLayout();
        }

    }

    private void creatStaticLayout() {
        if (TextUtil.isNull(getText().toString()) || getMaxLines() < 1 || mMaxLenthEnable) {
            mStaticLayout = null;
            return;
        }
        mStaticLayout = new StaticLayout(getText(), getPaint(), getWidth() - getPaddingLeft()
                - getPaddingRight(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        if (getMaxLines() >= 1) {
            if (mStaticLayout.getLineCount() > getMaxLines()) {
                int end = mStaticLayout.getLineEnd(getMaxLines() - 1) - 1;
                mStaticLayout = new StaticLayout(getText().subSequence(0, end) + ELLIPSIS, getPaint(),
                        getWidth() - getPaddingLeft() - getPaddingRight(),
                        Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        creatStaticLayout();
    }

    boolean mMaxLenthEnable = true;

    public void setMaxLenthEnable(boolean enable) {
        this.mMaxLenthEnable = enable;

        if (enable) {
            setFilters(mInputFilter);
            setHorizontallyScrolling(true);
        } else {
            setHorizontallyScrolling(false);
            setFilters(new InputFilter[0]);
            setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        }


    }

    /**
     * 是否显示最大长度
     */
    public void setMaxLengthShow(boolean show) {
        bShowMaxLength = show;
    }

    /**
     * 设置字数显示离边框右边的距离
     *
     * @param paddingBottomRight
     */
    public void setPaddingBottomRight(float paddingBottomRight) {
        this.paddingBottomRight = paddingBottomRight;
    }

    /**
     * 如果有控件需要根据字数来改变enable状态
     *
     * @param view
     */
    public void setEnableSwitchView(View view) {
        mEnableSwitchView = view;
    }

    private void setDrawable() {
        if (length() > 0) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, imgInable, null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (bShowDel && imgInable != null && isFocused()
                && event.getAction() == MotionEvent.ACTION_UP) {

            int eventX = (int) event.getRawX();
            int eventY = (int) event.getRawY();
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left = (int) (rect.right - 35 * DensityUtil
                    .getDensity(mContext));

            if (rect.left < eventX && eventX < rect.right) {
                setText("");
            }
        }
        return super.onTouchEvent(event);
    }

    public int getMaxLength() {
        int length = 0;
        try {
            InputFilter[] inputFilters = getFilters();
            for (InputFilter filter : inputFilters) {
                Class<?> c = filter.getClass();
                if (c.getName().equals("android.text.InputFilter$LengthFilter")) {
                    Field[] f = c.getDeclaredFields();
                    for (Field field : f) {
                        if (field.getName().equals("mMax")) {
                            field.setAccessible(true);
                            length = (Integer) field.get(filter);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return length;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mStaticLayout == null) {

            super.onDraw(canvas);
        } else {
            canvas.save();
            canvas.translate(0, getPaddingTop() + getHeight() / 2 - mStaticLayout.getHeight() / 2);
            mStaticLayout.draw(canvas);
            canvas.restore();
        }

        if (bShowMaxLength && maxLength > 0 && !bShowAllLength) {
            int leftsize = maxLength - this.getText().toString().length();
            if (leftsize <= bReduceNumber && leftsize > 10) {
                canvas.drawText(String.valueOf(leftsize), canvas.getWidth()
                                - hintWidth - paddingBottomRight,
                        canvas.getClipBounds().bottom - hintHeight,
                        textPaintBlack);
            } else if (leftsize <= 10) {
                canvas.drawText(String.valueOf(leftsize), canvas.getWidth()
                                - hintWidth - paddingBottomRight,
                        canvas.getClipBounds().bottom - hintHeight,
                        textPaintRed);
            }

        }
        if (bShowMaxLength && maxLength > 0 && bShowAllLength) {
            textPaintBlack.setTextAlign(Paint.Align.LEFT);
            int size = this.getText().toString().length();
            canvas.drawText(String.valueOf(size) + "/" + String.valueOf(maxLength), canvas.getWidth()
                            - hintWidth * 2 - paddingBottomRight,
                    canvas.getClipBounds().bottom - hintHeight,
                    textPaintBlack);

        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        setDelIconShow(enabled);

    }
}

