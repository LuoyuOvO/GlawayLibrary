package www.glaway.baselibrary.face;


import www.glaway.baselibrary.bean.CallMessage;

/**
 * FRagment回调接口
 */
public interface FragmentCallback {
    void sendFragmentNotification(CallMessage fragmentMessage);
}
