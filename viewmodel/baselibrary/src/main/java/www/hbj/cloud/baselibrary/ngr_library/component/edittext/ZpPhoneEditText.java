package www.hbj.cloud.baselibrary.ngr_library.component.edittext;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

/**
 * 自定义edittext，加入删除及显示剩余字数
 *
 * @author Administrator
 */

public class ZpPhoneEditText extends AppCompatEditText implements TextWatcher {

    // 特殊下标位置
    private static final int PHONE_INDEX_3 = 3;
    private static final int PHONE_INDEX_4 = 4;
    private static final int PHONE_INDEX_8 = 8;
    private static final int PHONE_INDEX_9 = 9;

    public ZpPhoneEditText(Context context) {
        super(context);
    }

    public ZpPhoneEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ZpPhoneEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        super.onTextChanged(s, start, before, count);
        if (s == "" || s.length() == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i != PHONE_INDEX_3 && i != PHONE_INDEX_8 && s.charAt(i) == ' ') {
                continue;
            } else {
                sb.append(s.charAt(i));
                if ((sb.length() == PHONE_INDEX_4 || sb.length() == PHONE_INDEX_9) && sb.charAt(sb.length() - 1) != ' ') {
                    sb.insert(sb.length() - 1, ' ');
                }
                if ((sb.length() == PHONE_INDEX_4 || sb.length() == PHONE_INDEX_9) && sb.charAt(sb.length() - 1) == ' ') {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }

        if (!sb.toString().equals(s.toString())) {
            setText(sb.toString());
            setSelection(sb.toString().length());
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

}
