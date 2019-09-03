package me.maxwell.common.utils.postgre.explain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import me.maxwell.common.core.JsonHelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Maxwell.Lee
 * @version 3.8.1
 * @company Scho Techonlogy Co. Ltd
 * @date 2019/8/28 10:11
 */
public class ExplainPlanNode {

    private static final Set<String> ActualExecutionTimeIsSelfNodes = new HashSet<>();

    static {
//        ActualExecutionTimeIsSelfNodes.add("Redistribute Motion");
//        ActualExecutionTimeIsSelfNodes.add("Broadcast Motion");
        ActualExecutionTimeIsSelfNodes.add("Materialize");
    }

    private ExplainPlanNoteLite body;

    private Float   selfExecutionTime;

    private Float   sumOfExecutionTime;

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

    }

    public ExplainPlanNoteLite getBody() {
        return body;
    }

    public Float    getSelfExecutionTime() {
        if (selfExecutionTime == null) {
            this.selfExecutionTime = body.getActualTotalTime() - body.getActualStartupTime();
        }

        return selfExecutionTime;
    }

    public Float    getSumOfExecutionTime() {
        if (sumOfExecutionTime == null) {
            sumOfExecutionTime = getSelfExecutionTime() + getTotalExecutionTimeOfSubNotes();
        }

        return sumOfExecutionTime;
    }

    private Float getTotalExecutionTimeOfSubNotes() {
        if (subNotes == null || subNotes.isEmpty()) return 0.0f;

        float sum = 0.0f;
        for (ExplainPlanNode note : subNotes) {
            sum += note.getSumOfExecutionTime();
        }

        return sum;
    }

    public List<ExplainPlanNode> getSubNotes() {
        return subNotes;
    }
}
