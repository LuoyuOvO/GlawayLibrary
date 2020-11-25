package www.glaway.baselibrary.bean;

import android.os.Bundle;

import java.util.List;
import java.util.Map;

/**
 * 回调传递消息
 */
public class CallMessage {


    private int type;
    private String message;
    private List<Map<String, Object>> messageListMap;
    private Map<String, Object> messageMap;
    private Bundle bundle;

    public CallMessage(int type, Bundle bundle) {
        this.type = type;
        this.bundle = bundle;
    }

    public CallMessage(int type, String message) {
        this.type = type;
        this.message = message;
    }
    public CallMessage(int type, List<Map<String, Object>> messageListMap) {
        this.type = type;
        this.messageListMap = messageListMap;
    }
    public CallMessage(int type, Map<String, Object> messageMap) {
        this.type = type;
        this.messageMap = messageMap;
    }


    public CallMessage() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Map<String, Object>> getMessageListMap() {
        return messageListMap;
    }

    public void setMessageListMap(List<Map<String, Object>> messageListMap) {
        this.messageListMap = messageListMap;
    }

    public Map<String, Object> getMessageMap() {
        return messageMap;
    }

    public void setMessageMap(Map<String, Object> messageMap) {
        this.messageMap = messageMap;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }
}
