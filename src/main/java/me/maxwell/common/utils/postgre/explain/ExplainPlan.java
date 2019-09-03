package me.maxwell.common.utils.postgre.explain;

import com.alibaba.fastjson.JSONObject;
import me.maxwell.common.core.JsonHelper;

/**
 * @author Maxwell.Lee
 * @version 3.8.1
 * @company Scho Techonlogy Co. Ltd
 * @date 2019/8/29 11:35
 */
public class ExplainPlan {

    private ExplainPlanNode node;

    private ExplainPlanStatementStatistics  statementStatistics;

    private Float   executionTime;

    private Float   planningTime;

    public ExplainPlan() {

    }

    public ExplainPlan(JSONObject object) {
        executionTime = JsonHelper.getFloatLikeName(object,"Execution-Time");
        planningTime = JsonHelper.getFloatLikeName(object,"Planning-Time");

        JSONObject ssObj = JsonHelper.getJSONObjectLikeName(object, "Statement-statistics");
        if (ssObj != null) {
            statementStatistics = new ExplainPlanStatementStatistics(ssObj);
        }

        this.node = new ExplainPlanNode(JsonHelper.getJSONObjectLikeName(object,"Plan"));
    }

    public ExplainPlanNode getNode() {
        return node;
    }

    public void setNode(ExplainPlanNode node) {
        this.node = node;
    }

    public ExplainPlanStatementStatistics getStatementStatistics() {
        return statementStatistics;
    }

    public void setStatementStatistics(ExplainPlanStatementStatistics statementStatistics) {
        this.statementStatistics = statementStatistics;
    }

    public Float getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Float executionTime) {
        this.executionTime = executionTime;
    }

    public Float getPlanningTime() {
        return planningTime;
    }

    public void setPlanningTime(Float planningTime) {
        this.planningTime = planningTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ExplainPlan{");
        sb.append("node=").append(node);
        sb.append(", statementStatistics=").append(statementStatistics);
        sb.append(", executionTime=").append(executionTime);
        sb.append(", planningTime=").append(planningTime);
        sb.append('}');
        return sb.toString();
    }
}
