package www.glaway.baselibrary.utils;

import com.blankj.utilcode.util.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.greenrobot.greendao.AbstractDao;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * json工具
 */
public class JSONUtil {
    /**
     * 除去jsonObject中空值
     */
    public static String removeNullAttr(String jsonArrray) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(jsonArrray);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                // 去掉value为空的键值对
                Iterator<String> keys = jsonObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (StringUtils.isEmpty(jsonObject.getString(next))) {
                        keys.remove();
                    }
                }
            }
        } catch (JSONException e) {
            return null;
        }
        return jsonArray.toString();
    }

    /**
     * lombok的toString转json
     */
    public static String toJsonStr(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        str = str.replace(")", "}");
        while (str.indexOf("(") != -1) {
            int i = str.indexOf("(");
            str = str.substring(0, str.lastIndexOf("=", i) + 1) + "{" + str.substring(i + 1);
        }
        str = str.replace(" ", "")
                .replace("=", "\"=\"")
                .replace(",", "\",\"")
                .replace("\"null\"", "null")
                .replace("\"{", "{\"")
                .replace("}\"", "}")
                .replace("}", "\"}")
                .replace("\"}\"}", "\"}}");

        str = "\"" + str.replace("=", ":");
        if ("\"{".equals(str.substring(0, 2))) {
            str = "{\"" + str.substring(2);
        } else {
            str = str.substring(str.indexOf(":") + 1);
        }
        str = str.replace("\"null\"", "\"\"");
        str = str.replace("null", "\"\"");
        return str;
    }

    /**
     * ListData转换成jsonarrary
     */
    public static String ListStringToJsonArr(List<String> list) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{\"RECORDS\":[");
        list.forEach(f -> {
            stringBuffer.append(f + ",");
        });
        if (list.size() > 0) stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length());
        stringBuffer.append("]}");
        return stringBuffer.toString();
    }


    public static String listToJson(List list) {
        ObjectMapper objectMapper = getObjectMapper();

        try {
            String listJsonStr = objectMapper.writeValueAsString(list);
            return listJsonStr;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
//        //list转json字符串
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append("[");
//        try {
//            for (Object f : list) {
//                ObjectNode jsonNodes = objectMapper.valueToTree(f);
//                jsonNodes.put("tasknumber", "");
//                jsonNodes.put("schedulenum", "");
//                String newJson = objectMapper.writeValueAsString(jsonNodes);
//                stringBuffer.append(newJson);
//                stringBuffer.append(",");
//            }
//            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
//            stringBuffer.append("]");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        return stringBuffer.toString();
    }

    public static List jsonToList(String json) {
        if (json == null || json.equals("")) {
            return null;
        }
        ObjectMapper objectMapper = getObjectMapper();
        List<AbstractDao> abstractDaos = null;
        //json字符串转list
        try {
            abstractDaos = objectMapper.readValue(json, new TypeReference<List<AbstractDao>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return abstractDaos;
    }
    public static ObjectMapper getObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        //反序列化的时候如果多了其他属性,不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //如果是空对象的时候,不抛异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return objectMapper;
    }
}
