package task8.utils;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;
import java.util.*;

public class JSONUtils {

    public static String getValue(JSONObject jObj, String findKey) throws JSONException {
        String finalValue = "";
        if (jObj == null) {
            return "";
        }

        Iterator<String> keyItr = jObj.keys();
        Map<String, Object> map = new HashMap<>();

        while(keyItr.hasNext()) {
            String key = keyItr.next();
            map.put(key, jObj.get(key));
        }

        for (Map.Entry<String, Object> pair : map.entrySet()) {
            String key = pair.getKey();
            if (key.equalsIgnoreCase(findKey)) {
                return jObj.getString(key);
            }
            Object value = jObj.get(key);

            if (value instanceof JSONObject) {
                finalValue = getValue((JSONObject)value, findKey);
            }
            if (jObj.get(key) instanceof JSONArray ) {
                JSONArray jar = (JSONArray)jObj.get(key);
                for (int i = 0; i < jar.length(); i++) {
                    JSONObject j = jar.getJSONObject(i);
                    finalValue = getValue(j, findKey);
                }
            }
        }
        return finalValue;
    }

}
