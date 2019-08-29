package me.maxwell.common.utils.postgre.explain;

import me.maxwell.common.core.ShortCodeGenerator;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.commons.text.StringEscapeUtils;

import java.util.Date;

/**
 * @author Maxwell.Lee
 * @version 3.8.1
 * @company Scho Techonlogy Co. Ltd
 * @date 2019/8/29 16:48
 */
public class ExplainPlan2SqlCmd {

    private static final ShortCodeGenerator UuidGen = new ShortCodeGenerator(32);

    private static final FastDateFormat DateFormat = DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT;

    private static final String InsertIntoExplainPlanTbl = "INSERT INTO pg_explain_plan (uuid,sql_name,memory_used,memory_wanted,execution_time,planning_time,state,create_time) VALUES";
    private static final String InsertIntoExplainPlanNoteTbl = "INSERT INTO pg_explain_plan_note (uuid,parent_uuid,plan_uuid,self_execution_time,note_type,parent_relationship,strategy,slice,segments,alias,relation,relation_name,gang_type,startup_cost,total_cost,plan_rows,plan_width,actual_startup_time,actual_total_time,actual_rows,actual_loops,share_id,slice_id,senders,receivers,filter,rows_removed_by_filter,join_type,join_filter,rows_removed_by_join_filter,sort_key,sort_method,sort_space_used,sort_space_type,merge_key,group_key,hash_key,hash_cond,extra_text,create_time,state) VALUES ";

    public StringBuilder    toSQL(String sqlName, ExplainPlan plan) {
        StringBuilder buf = new StringBuilder();

        String planUuid = UuidGen.next();

        buf.append("\r\n").append(String.format("-- %s\r\n", sqlName));
        outputExplainPlanTblSql(buf, sqlName, planUuid, plan);

        buf.append("\r\n");

        buf.append(InsertIntoExplainPlanNoteTbl).append("\r\n");
        outputPlanNodeTblSql(buf, planUuid, null, plan.getNode(), true);
        buf.append(";\r\n");

        return buf;
    }

    private void outputExplainPlanTblSql(StringBuilder buf, String sqlName, String uuid, ExplainPlan plan) {
        int count = 0;

        buf.append(InsertIntoExplainPlanTbl).append(" (");

        outputFiled(buf, count++, uuid);
        outputFiled(buf, count++, sqlName);
        if (plan.getStatementStatistics() != null) {
            outputFiled(buf, count++, plan.getStatementStatistics().getMemoryUsed());
            outputFiled(buf, count++, plan.getStatementStatistics().getMemoryWanted());
        } else {
            outputFiled(buf, count++, 0);
            outputFiled(buf, count++, 0);
        }

        outputFiled(buf, count++, plan.getExecutionTime());
        outputFiled(buf, count++, plan.getPlanningTime());
        outputFiled(buf, count++, 1);
        outputFiled(buf, count++, new Date());

        buf.append(");\r\n");
    }

    private void outputPlanNodeTblSql(StringBuilder buf, String planUuid, String parentUuid,
                                      ExplainPlanNode node, boolean isFirst) {
        if (node == null) return ;

        int count = 0;

        if (isFirst) {
            buf.append("(");
        } else {
            buf.append(",(");
        }


        String uuid = UuidGen.next();
        outputFiled(buf, count++, uuid);
        outputFiled(buf, count++, parentUuid);
        outputFiled(buf, count++, planUuid);
        outputFiled(buf, count++, node.getSelfExecutionTime());
        outputFiled(buf, count++, node.getBody().getNodeType());
        outputFiled(buf, count++, node.getBody().getParentRelationship());
        outputFiled(buf, count++, node.getBody().getStrategy());
        outputFiled(buf, count++, node.getBody().getSlice());
        outputFiled(buf, count++, node.getBody().getSegments());
        outputFiled(buf, count++, node.getBody().getAlias());
        outputFiled(buf, count++, node.getBody().getRelation());
        outputFiled(buf, count++, node.getBody().getRelationName());
        outputFiled(buf, count++, node.getBody().getGangType());
        outputFiled(buf, count++, node.getBody().getStartupCost());
        outputFiled(buf, count++, node.getBody().getTotalCost());
        outputFiled(buf, count++, node.getBody().getPlanRows());
        outputFiled(buf, count++, node.getBody().getPlanWidth());
        outputFiled(buf, count++, node.getBody().getActualStartupTime());
        outputFiled(buf, count++, node.getBody().getActualTotalTime());
        outputFiled(buf, count++, node.getBody().getActualRows());
        outputFiled(buf, count++, node.getBody().getActualLoops());
        outputFiled(buf, count++, node.getBody().getShareID());
        outputFiled(buf, count++, node.getBody().getSliceID());
        outputFiled(buf, count++, node.getBody().getSenders());
        outputFiled(buf, count++, node.getBody().getReceivers());
        outputFiled(buf, count++, node.getBody().getFilter());
        outputFiled(buf, count++, node.getBody().getRowsRemovedByFilter());
        outputFiled(buf, count++, node.getBody().getJoinType());
        outputFiled(buf, count++, node.getBody().getJoinFilter());
        outputFiled(buf, count++, node.getBody().getRowsRemovedByJoinFilter());
        outputFiled(buf, count++, node.getBody().getSortKey());
        outputFiled(buf, count++, node.getBody().getSortMethod());
        outputFiled(buf, count++, node.getBody().getSortSpaceUsed());
        outputFiled(buf, count++, node.getBody().getSortSpaceType());
        outputFiled(buf, count++, node.getBody().getMergeKey());
        outputFiled(buf, count++, node.getBody().getGroupKey());
        outputFiled(buf, count++, node.getBody().getHashKey());
        outputFiled(buf, count++, node.getBody().getHashCond());
        outputFiled(buf, count++, node.getBody().getExtraText());
        outputFiled(buf, count++, new Date());
        outputFiled(buf, count++, 1);

        buf.append(")\r\n");

        if (node.getSubNotes() != null && node.getSubNotes().size() > 0) {
            for (ExplainPlanNode sn : node.getSubNotes()) {
                outputPlanNodeTblSql(buf, planUuid, uuid, sn, false);
            }
        }
    }

    private void outputFiled(StringBuilder buf, int count, Object val) {
        if (count > 0) {
            buf.append(", ");
        }

        buf.append(formatFiledVal(val));
    }

    private String formatFiledVal(Object val) {
        if (val == null) return "NULL";

        if (val instanceof String) {
            return "\"" + StringEscapeUtils.escapeJson((String) val) + "\"";
        } else if (val instanceof Date) {
            return "\"" + DateFormat.format((Date)val) + "\"";
        } else {
            return val.toString();
        }
    }

}
