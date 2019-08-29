package me.maxwell.common.utils.postgre.explain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import me.maxwell.common.core.JsonHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maxwell.Lee
 * @version 3.8.1
 * @company Scho Techonlogy Co. Ltd
 * @date 2019/8/28 10:11
 */
public class ExplainPlanNode {

    private ExplainPlanNoteLite body;

    private Float   selfExecutionTime;

    private List<ExplainPlanNode>   subNotes;

    public ExplainPlanNode() {

    }

    public ExplainPlanNode(JSONObject object) {
        this.body = new ExplainPlanNoteLite(object);
        this.subNotes = buildSubNotes(object);

        repairData();
    }

    private static List<ExplainPlanNode> buildSubNotes(JSONObject object) {
        Object subs = JsonHelper.getObject(object, "Plan", "Plans");

        if (subs == null) return null;

        List<ExplainPlanNode> rst = new ArrayList<>();

        if (subs instanceof JSONArray) {
            JSONArray array = (JSONArray) subs;
            for (int i = 0; i < array.size(); i++) {
                rst.add(new ExplainPlanNode(array.getJSONObject(i)));
            }

            return rst;
        }

        if (subs instanceof JSONObject) {
            JSONObject subObj = (JSONObject) subs;

            if (subObj.size() > 1) {
                rst.add(new ExplainPlanNode(subObj));
                return rst;
            } else {
                return buildSubNotes(subObj);
            }
        }


        throw new RuntimeException("不能识别的类型");
    }

    private void repairData() {
        if (body == null || body.getActualTotalTime() == null) {
            System.out.println("==Body is null==");
        }

        if (body.getActualTotalTime() == 0.0f) {
            body.setActualStartupTime(getMinActualStartupTimeOfSubNotes());
            body.setActualTotalTime(getActualTotalTimeOfSubNotes());
            body.setActualRows(getSumOfActualRowsOfSubNotes());
        }
    }

    public ExplainPlanNoteLite getBody() {
        return body;
    }

    public Float getSelfExecutionTime() {
        if (selfExecutionTime == null) {
            selfExecutionTime = body.getActualTotalTime() - getActualTotalTimeOfSubNotes();
        }

        return selfExecutionTime;
    }

    private Long getSumOfActualRowsOfSubNotes() {
        if (subNotes == null || subNotes.isEmpty()) return 0L;

        long sum = 0;
        for (ExplainPlanNode note : subNotes) {
            sum += note.getBody().getActualRows();
        }

        return sum;
    }

    private Float getActualTotalTimeOfSubNotes() {
        if (subNotes == null || subNotes.isEmpty()) return 0.0f;

        float sum = 0.0f;
        for (ExplainPlanNode note : subNotes) {
            sum += note.getBody().getActualTotalTime();
        }

        return sum;
    }

    private Float   getMinActualStartupTimeOfSubNotes() {
        if (subNotes == null || subNotes.isEmpty()) return 0.0f;

        Float ast = subNotes.get(0).getBody().getActualStartupTime();
        for (ExplainPlanNode note : subNotes) {
            if (ast <= note.getBody().getActualStartupTime()) {
                ast = note.getBody().getActualStartupTime();
            }
        }

        return ast;
    }

    public List<ExplainPlanNode> getSubNotes() {
        return subNotes;
    }
}
