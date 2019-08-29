package me.maxwell.common.utils.postgre.explain;

import com.alibaba.fastjson.JSONObject;
import me.maxwell.common.core.JsonHelper;

/**
 * @author Maxwell.Lee
 * @version 3.8.1
 * @company Scho Techonlogy Co. Ltd
 * @date 2019/8/28 10:52
 */
public class ExplainPlanNoteLite {

    private String  nodeType;

    private String  parentRelationship;

    private String  strategy;

    private Integer slice;

    private Integer segments;

    private String  gangType;

    private String  joinType;

    private Float   startupCost;

    private Float   totalCost;

    private Long    planRows;

    private Integer planWidth;

    private Float   actualStartupTime;

    private Float   actualTotalTime;

    private Long    actualRows;

    private Integer actualLoops;

    private String  shareID;

    private String  sliceID;

    private String  filter;

    private String  joinFilter;

    private Long    rowsRemovedByJoinFilter;

    private Long    rowsRemovedByFilter;

    private Integer senders;

    private Integer receivers;

    private String  alias;

    private String  relation;

    private String  relationName;

    private String  extraText;

    private String  sortKey;

    private String  sortMethod;

    private Integer sortSpaceUsed;

    private String  sortSpaceType;

    private String  mergeKey;

    private String  groupKey;

    private String  hashKey;

    private String  hashCond;

    public ExplainPlanNoteLite() {

    }

    public ExplainPlanNoteLite(JSONObject object) {
        this.nodeType = JsonHelper.getStringLikeName(object, "Node-Type");
        this.parentRelationship = JsonHelper.getStringLikeName(object, "Parent-Relationship");
        this.strategy = JsonHelper.getStringLikeName(object, "Strategy");
        this.relation = JsonHelper.getStringLikeName(object, "Relation");
        this.relationName = JsonHelper.getStringLikeName(object, "Relation-Name");
        this.alias = JsonHelper.getStringLikeName(object, "Alias");

        this.slice = JsonHelper.getIntegerLikeName(object, "Slice");
        this.segments = JsonHelper.getIntegerLikeName(object, "Segments");
        this.gangType = JsonHelper.getStringLikeName(object, "Gang-Type");

        this.shareID = JsonHelper.getStringLikeName(object, "Share-ID");
        this.sliceID = JsonHelper.getStringLikeName(object, "Slice-ID");

        this.startupCost = JsonHelper.getFloatLikeName(object, "Startup-Cost");
        this.totalCost = JsonHelper.getFloatLikeName(object, "Total-Cost");
        this.planRows = JsonHelper.getLongLikeName(object, "Plan-Rows");
        this.planWidth = JsonHelper.getIntegerLikeName(object, "Plan-Width");

        this.actualStartupTime = JsonHelper.getFloatLikeName(object, "Actual-Startup-Time");
        if (this.actualStartupTime == null) this.actualStartupTime = 0.0f;

        this.actualTotalTime = JsonHelper.getFloatLikeName(object, "Actual-Total-Time");
        if (this.actualTotalTime == null) this.actualTotalTime = 0.0f;

        this.actualRows = JsonHelper.getLongLikeName(object, "Actual-Rows");
        this.actualLoops = JsonHelper.getIntegerLikeName(object, "Actual-Loops");

        this.sortKey = JsonHelper.getStringLikeName(object, "Sort-Key");
        this.sortMethod = JsonHelper.getStringLikeName(object, "Sort-Method");
        this.sortSpaceUsed = JsonHelper.getIntegerLikeName(object, "Sort-Space-Used");
        this.sortSpaceType = JsonHelper.getStringLikeName(object, "Sort-Space-Type");

        this.mergeKey = JsonHelper.getStringLikeName(object, "Merge-Key");
        this.groupKey = JsonHelper.getStringLikeName(object, "Group-Key");
        this.hashKey = JsonHelper.getStringLikeName(object, "Hash-Key");
        this.hashCond = JsonHelper.getStringLikeName(object, "Hash-Cond");

        this.senders = JsonHelper.getIntegerLikeName(object, "Senders");
        this.receivers = JsonHelper.getIntegerLikeName(object, "Receivers");

        this.filter = JsonHelper.getStringLikeName(object, "Filter");
        this.rowsRemovedByFilter = JsonHelper.getLongLikeName(object, "Rows-Removed-by-Filter");
        this.joinType = JsonHelper.getStringLikeName(object, "Join-Type");
        this.joinFilter = JsonHelper.getStringLikeName(object, "Join-Filter");
        this.rowsRemovedByJoinFilter = JsonHelper.getLongLikeName(object, "Rows-Removed-by-Join-Filter");

        this.extraText = JsonHelper.getStringLikeName(object, "Extra-Text");
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getParentRelationship() {
        return parentRelationship;
    }

    public void setParentRelationship(String parentRelationship) {
        this.parentRelationship = parentRelationship;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public Integer getSlice() {
        return slice;
    }

    public void setSlice(Integer slice) {
        this.slice = slice;
    }

    public Integer getSegments() {
        return segments;
    }

    public void setSegments(Integer segments) {
        this.segments = segments;
    }

    public String getGangType() {
        return gangType;
    }

    public void setGangType(String gangType) {
        this.gangType = gangType;
    }

    public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }

    public Float getStartupCost() {
        return startupCost;
    }

    public void setStartupCost(Float startupCost) {
        this.startupCost = startupCost;
    }

    public Float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Float totalCost) {
        this.totalCost = totalCost;
    }

    public Long getPlanRows() {
        return planRows;
    }

    public void setPlanRows(Long planRows) {
        this.planRows = planRows;
    }

    public Integer getPlanWidth() {
        return planWidth;
    }

    public void setPlanWidth(Integer planWidth) {
        this.planWidth = planWidth;
    }

    public Float getActualStartupTime() {
        return actualStartupTime;
    }

    public void setActualStartupTime(Float actualStartupTime) {
        this.actualStartupTime = actualStartupTime;
    }

    public Float getActualTotalTime() {
        return actualTotalTime;
    }

    public void setActualTotalTime(Float actualTotalTime) {
        this.actualTotalTime = actualTotalTime;
    }

    public Long getActualRows() {
        return actualRows;
    }

    public void setActualRows(Long actualRows) {
        this.actualRows = actualRows;
    }

    public Integer getActualLoops() {
        return actualLoops;
    }

    public void setActualLoops(Integer actualLoops) {
        this.actualLoops = actualLoops;
    }

    public String getShareID() {
        return shareID;
    }

    public void setShareID(String shareID) {
        this.shareID = shareID;
    }

    public String getSliceID() {
        return sliceID;
    }

    public void setSliceID(String sliceID) {
        this.sliceID = sliceID;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getJoinFilter() {
        return joinFilter;
    }

    public void setJoinFilter(String joinFilter) {
        this.joinFilter = joinFilter;
    }

    public Long getRowsRemovedByJoinFilter() {
        return rowsRemovedByJoinFilter;
    }

    public void setRowsRemovedByJoinFilter(Long rowsRemovedByJoinFilter) {
        this.rowsRemovedByJoinFilter = rowsRemovedByJoinFilter;
    }

    public Long getRowsRemovedByFilter() {
        return rowsRemovedByFilter;
    }

    public void setRowsRemovedByFilter(Long rowsRemovedByFilter) {
        this.rowsRemovedByFilter = rowsRemovedByFilter;
    }

    public Integer getSenders() {
        return senders;
    }

    public void setSenders(Integer senders) {
        this.senders = senders;
    }

    public Integer getReceivers() {
        return receivers;
    }

    public void setReceivers(Integer receivers) {
        this.receivers = receivers;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public String getExtraText() {
        return extraText;
    }

    public void setExtraText(String extraText) {
        this.extraText = extraText;
    }

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }

    public String getSortMethod() {
        return sortMethod;
    }

    public void setSortMethod(String sortMethod) {
        this.sortMethod = sortMethod;
    }

    public Integer getSortSpaceUsed() {
        return sortSpaceUsed;
    }

    public void setSortSpaceUsed(Integer sortSpaceUsed) {
        this.sortSpaceUsed = sortSpaceUsed;
    }

    public String getSortSpaceType() {
        return sortSpaceType;
    }

    public void setSortSpaceType(String sortSpaceType) {
        this.sortSpaceType = sortSpaceType;
    }

    public String getMergeKey() {
        return mergeKey;
    }

    public void setMergeKey(String mergeKey) {
        this.mergeKey = mergeKey;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    public String getHashCond() {
        return hashCond;
    }

    public void setHashCond(String hashCond) {
        this.hashCond = hashCond;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ExplainPlanNoteLite{");
        sb.append("nodeType='").append(nodeType).append('\'');
        sb.append(", parentRelationship='").append(parentRelationship).append('\'');
        sb.append(", strategy='").append(strategy).append('\'');
        sb.append(", slice=").append(slice);
        sb.append(", segments=").append(segments);
        sb.append(", gangType='").append(gangType).append('\'');
        sb.append(", joinType='").append(joinType).append('\'');
        sb.append(", startupCost=").append(startupCost);
        sb.append(", totalCost=").append(totalCost);
        sb.append(", planRows=").append(planRows);
        sb.append(", planWidth=").append(planWidth);
        sb.append(", actualStartupTime=").append(actualStartupTime);
        sb.append(", actualTotalTime=").append(actualTotalTime);
        sb.append(", actualRows=").append(actualRows);
        sb.append(", actualLoops=").append(actualLoops);
        sb.append(", shareID='").append(shareID).append('\'');
        sb.append(", sliceID='").append(sliceID).append('\'');
        sb.append(", filter='").append(filter).append('\'');
        sb.append(", joinFilter='").append(joinFilter).append('\'');
        sb.append(", rowsRemovedByJoinFilter=").append(rowsRemovedByJoinFilter);
        sb.append(", rowsRemovedByFilter=").append(rowsRemovedByFilter);
        sb.append(", senders=").append(senders);
        sb.append(", receivers=").append(receivers);
        sb.append(", alias='").append(alias).append('\'');
        sb.append(", relation='").append(relation).append('\'');
        sb.append(", relationName='").append(relationName).append('\'');
        sb.append(", extraText='").append(extraText).append('\'');
        sb.append(", sortKey='").append(sortKey).append('\'');
        sb.append(", sortMethod='").append(sortMethod).append('\'');
        sb.append(", sortSpaceUsed=").append(sortSpaceUsed);
        sb.append(", sortSpaceType='").append(sortSpaceType).append('\'');
        sb.append(", mergeKey='").append(mergeKey).append('\'');
        sb.append(", groupKey='").append(groupKey).append('\'');
        sb.append(", hashKey='").append(hashKey).append('\'');
        sb.append(", hashCond='").append(hashCond).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
