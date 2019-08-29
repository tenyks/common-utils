package me.maxwell.common.utils.postgre.explain;

import com.alibaba.fastjson.JSONObject;
import me.maxwell.common.core.JsonHelper;

/**
 * @author Maxwell.Lee
 * @version 3.8.1
 * @company Scho Techonlogy Co. Ltd
 * @date 2019/8/28 10:38
 */
public class ExplainPlanStatementStatistics {

    private Integer     memoryUsed;

    private Integer     memoryWanted;

    public ExplainPlanStatementStatistics() {

    }

    public ExplainPlanStatementStatistics(JSONObject object) {
        this.memoryUsed = JsonHelper.getIntegerLikeName(object, "Memory-used");
        this.memoryWanted = JsonHelper.getIntegerLikeName(object, "Memory-wanted");
    }

    public Integer getMemoryUsed() {
        return memoryUsed;
    }

    public void setMemoryUsed(Integer memoryUsed) {
        this.memoryUsed = memoryUsed;
    }

    public Integer getMemoryWanted() {
        return memoryWanted;
    }

    public void setMemoryWanted(Integer memoryWanted) {
        this.memoryWanted = memoryWanted;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ExplainPlanStatementStatistics{");
        sb.append("memoryUsed=").append(memoryUsed);
        sb.append(", memoryWanted=").append(memoryWanted);
        sb.append('}');
        return sb.toString();
    }
}
