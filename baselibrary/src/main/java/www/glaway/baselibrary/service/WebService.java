package www.glaway.baselibrary.service;

import android.os.AsyncTask;

import com.blankj.utilcode.util.StringUtils;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Map;

/**
 * wenservice工具类
 *
 *当线程池中线程都有任务正在执行且缓冲队列已满时，继续往线程池中提交任务则会报异常  由于手机内核不同， <内核*2+1+128
 */
public class WebService extends AsyncTask<Void, Integer, SoapObject> {

//    private static String SOAP_ACTION = "http://webservice.mro.glaway.com/getAssetDjx";
//    private static String NAMESPACE = "http://webservice.mro.glaway.com/";
//    private static String METHOD_NAME = "getRcbzgz";
//    private static String URL = "http://192.168.11.59:58000/gwmro/services/MroWebService?wsdl";

//    private String namespace = "http://webservice.mro.glaway.com/";
    private String namespace;
    private String methodName;
    private String url;
//    private String ip;
    private String soapAction;
    private Map<String, String> params;
    private WebCallback webCallback;

    public WebService(String methodName,String url,String namespace,String  soapAction, WebCallback webCallback) {
        this.methodName = methodName;
        this.webCallback = webCallback;
//        ip = PmaUtil.getIpCache();
        this.url = url;
        this.namespace = namespace;
        this.soapAction = soapAction;
//        url = "http://" + ip + ":58000/gwmro/services/MroAndroidPmaWebService?wsdl";
//        soapAction = "http://webservice.mro.glaway.com/" + methodName;
    }

    public WebService(String methodName,String url,String namespace,String  soapAction, Map<String, String> params, WebCallback webCallback) {
        this.params = params;
        this.methodName = methodName;
        this.webCallback = webCallback;
        this.url = url;
        this.namespace = namespace;
        this.soapAction = soapAction;
//        ip = PmaUtil.getIpCache();
//        url = "http://" + ip + ":58000/gwmro/services/MroAndroidPmaWebService?wsdl";
//        soapAction = "http://webservice.mro.glaway.com/" + methodName;
    }

    /**
     * 开始前操作
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * 行任务中的耗时操作、返回 线程任务执行的结果
     */
    @Override
    protected SoapObject doInBackground(Void... voids) {
        SoapObject result = null;
        if (StringUtils.isEmpty(url)){
            return result;
        }
        SoapObject request = new SoapObject(namespace, methodName);
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String k = entry.getKey();
                String v = entry.getValue();
                request.addProperty(k, v);
            }
        }
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transportSE = new HttpTransportSE(url,3000);
        try {
            transportSE.call(soapAction, envelope);
            return (SoapObject) envelope.bodyIn;//获取到返回的结果，并强制转换成SoapObject对象
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 在主线程 显示线程任务执行的进度
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        webCallback.progress(values);
    }

    /**
     * 接收线程任务执行结果、将执行结果显示到UI组件
     */
    @Override
    protected void onPostExecute(SoapObject data) {
        if (data == null) {
            if (StringUtils.isEmpty(url)){
                webCallback.fail("获取url地址失败！请在个人设置中设置");
            }else {
                webCallback.fail("请求错误！");
            }
            return;
        }
        StringBuilder builder = new StringBuilder();
        //解析返回的数据
        for (int i = 0; i < data.getPropertyCount(); i++) {
            builder.append(data.getProperty(i));
        }
        webCallback.success(builder.toString());
    }

    /**
     * 将异步任务设置为：取消状态
     */
    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    public interface WebCallback {
        void success(Object call);

        void fail(Object call);

        void progress(Object call);
    }


    /**
     * 发送数据：
     *         WebService webService = new WebService(methodName, params, new WebService.WebCallback() {
     *             @Override
     *             public void success(Object call) {
     *                 callBack.success(call);
     *             }
     *
     *             @Override
     *             public void fail(Object call) {
     *                 callBack.fail(call);
     *             }
     *
     *             @Override
     *             public void progress(Object call) {
     *
     *             }
     *         });
     *         webService.executeOnExecutor(THREAD_POOL_EXECUTOR);
     *
     *  获取数据：
     *
     *            WebService webService = new WebService(entry.getKey(),new WebService.WebCallback() {
     *                 @Override
     *                 public void success(Object call) {
     *                     try {
     *                         JSONObject jsonObject = new JSONObject(call.toString());
     *                         String records = jsonObject.getString("RECORDS");
     *
     *                     } catch (Exception e) {
     *                         e.printStackTrace();
     *                         ToastUtil.showError(""+entry.getKey()+"解析失败");
     *                     }
     *                 }
     *
     *                 @Override
     *                 public void fail(Object call) {
     *                     ToastUtil.showError("获取"+entry.getKey()+"失败!"+call);
     *                 }
     *
     *                 @Override
     *                 public void progress(Object call) {
     *
     *                 }
     *             });
     *             webService.executeOnExecutor(THREAD_POOL_EXECUTOR);
     */
}
