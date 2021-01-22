package www.hbj.cloud.baselibrary.ngr_library.component.timePickDialog;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * SysTimePickerDialog
 */
public class SysTimePickerDialog extends TimePickerDialog {

	public SysTimePickerDialog(Context context,
                               OnTimeSetListener callBack,
                               int hourOfDay, int minute, boolean is24HourView) {
		super(context, callBack, hourOfDay, minute, is24HourView);
	}

	public SysTimePickerDialog(Context context, int themeResId,
                               OnTimeSetListener listener, int hourOfDay, int minute,
                               boolean is24HourView) {
		super(context, themeResId, listener, hourOfDay, minute, is24HourView);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onStop() {

		//super.onStop();
	}

    @Override
    public void onClick(DialogInterface dialog, int which) {
        ((SysTimePickerDialog)dialog).getWindow().getDecorView().clearFocus();
        super.onClick(dialog, which);
    }
}
