package www.hbj.cloud.baselibrary.ngr_library.component.datePickDialog;

import android.app.DatePickerDialog;
import android.content.Context;

/**
 * SysDatePickerDialog
 */
public class SysDatePickerDialog  extends DatePickerDialog {
    public SysDatePickerDialog(Context context, OnDateSetListener callBack,
                               int year, int monthOfYear, int dayOfMonth) {
        super(context, callBack, year, monthOfYear, dayOfMonth);
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            int daySpinnerId = Resources.getSystem().getIdentifier("day", "id", "android");
//            if (daySpinnerId != 0) {
//                View daySpinner = this.findViewById(daySpinnerId);
//                if (daySpinner != null) {
//                    daySpinner.setVisibility(View.GONE);
//                }
//            }
//        }

    }

    @Override
    protected void onStop() {
        //super.onStop();
    }

}
