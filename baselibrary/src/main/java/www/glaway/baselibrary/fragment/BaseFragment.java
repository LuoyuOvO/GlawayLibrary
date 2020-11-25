package www.glaway.baselibrary.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import www.glaway.baselibrary.bean.CallMessage;

public abstract class BaseFragment extends Fragment {

    //二维码扫码使用
//    protected Typeface tfLight;
//    protected Typeface tfRegular;

    //回调数据
    private CallMessage callMessage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        tfLight = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");
//        tfRegular = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Regular.ttf");
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    /**
     * 初始化界面
     */
    public abstract void  initView();

    /**
     * 初始化数据
     */
    public abstract void  initData();

    public CallMessage getCallMessage() {
        return callMessage;
    }

    public void setCallMessage(CallMessage callMessage) {
        this.callMessage = callMessage;
    }
}
