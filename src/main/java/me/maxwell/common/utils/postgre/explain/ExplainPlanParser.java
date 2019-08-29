package me.maxwell.common.utils.postgre.explain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author Maxwell.Lee
 * @version 3.8.1
 * @company Scho Techonlogy Co. Ltd
 * @date 2019/8/28 20:39
 */
public class ExplainPlanParser {

    public static ExplainPlan parse(String jsonTxt) {
        Object obj = JSON.parse(jsonTxt);

        if (obj == null) return null;

        if (obj instanceof JSONArray) {
            JSONArray arrObj = (JSONArray) obj;

            if (arrObj.size() == 0) return null;

            return new ExplainPlan(arrObj.getJSONObject(0));
        } else {
            return new ExplainPlan((JSONObject) obj);
        }
    }

}
