package www.hbj.cloud.platform.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import www.hbj.cloud.baselibrary.common.base.fragment.BaseFragment;
import www.hbj.cloud.platform.databinding.FragmentNotificationsBinding;

/**
 * @author Alan-kun
 * @date 2020/12/21.
 * description：
 */
public class NotificationsFragment extends BaseFragment<FragmentNotificationsBinding,NotificationsModel> {
    @Override
    protected FragmentNotificationsBinding bindingView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentNotificationsBinding.inflate(inflater,container,false);
    }

    @Override
    protected Class VMClass() {
        return NotificationsModel.class;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {

    }
}

