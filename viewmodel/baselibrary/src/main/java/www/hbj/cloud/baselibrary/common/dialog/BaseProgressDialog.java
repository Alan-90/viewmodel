package www.hbj.cloud.baselibrary.common.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import www.hbj.cloud.baselibrary.R;


/**
 * @author Alan-kun
 * @date 2020/12/17.
 * description：
 */
public class BaseProgressDialog extends ProgressDialog {

    private String tips;
    TextView tv_title;
    public BaseProgressDialog(Context context) {
        super(context, R.style.baseProgressDialog);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.progress_circle);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(getTips());
    }

    @Override
    public void dismiss() {
        super.dismiss();
        dailog = null;
    }

    private static BaseProgressDialog dailog;

    public static BaseProgressDialog getDialogInstance(Context context)
    {
//		if(dailog==null)
        dailog = new BaseProgressDialog(context);
        return dailog;
    }
    public String getTips() {
        return tips;
    }
    public void setTips(String tips) {

        this.tips = tips;
        if(tv_title!=null) {
            tv_title.setText(tips);
        }
    }

}
