package www.hbj.cloud.baselibrary.common.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;


/**
 * @author Alan_zyk
 * @date 2020/12/20
 */

public abstract class BaseFragment<T extends ViewBinding, VM extends ViewModel> extends LazyLoadFragment  {


    protected abstract T bindingView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container);

    protected abstract Class<VM> VMClass();

    protected T binding;
    protected VM viewModel;


    private boolean isFirstLoad = true;
    private boolean needReset = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(VMClass());
        binding = bindingView(inflater, container);
        View view = binding.getRoot();
        return view;
    }

    @Override
    protected void onFragmentFirstVisible() {
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isFirstLoad && isFragmentVisible()) {
            onVisible();
        }
        if (isFirstLoad && needReset) {
            isFirstLoad = false;
        }
        if (!needReset) {
            needReset = true;
        }
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible && !isFirstLoad) {
            onVisible();
        }
        if (isFirstLoad && needReset) {
            isFirstLoad = false;
        }
        if (!needReset) {
            needReset = true;
        }
    }

    /**
     * Fragment 对于用户可见的状态下，执行当前方法。
     * 第一次可见不会走当前方法，Activity 重见，或者Fragment 切换可见，执行该方法。
     */
    public void onVisible() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(savedInstanceState);
    }

    protected abstract void init(@Nullable Bundle savedInstanceState);





    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
