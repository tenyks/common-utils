package me.maxwell.common.utils.postgre.explain;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class ExplainPlan2SqlCmdTest {


    @Test
    public void toSQL() throws IOException {
        String inputFile = "F:\\Share\\Demo\\greenplum\\benchmark\\SQL Explain.txt";
        String outputFile = "F:\\Share\\Demo\\greenplum\\benchmark\\SQL Explain.sql";

        ExplainPlan2SqlCmd cmd = new ExplainPlan2SqlCmd();

        FileInputStream fis = new FileInputStream(inputFile);
        List<String> lines = IOUtils.readLines(fis);
        fis.close();

        FileOutputStream fos = new FileOutputStream(outputFile);
        for (String line : lines) {
            if (line.length() < 8) continue;

            String sqlName = line.substring(0, 8);
            String jsonTxt = line.substring(8);

            if (jsonTxt.length() < 5) {
                System.out.println(String.format("====忽略SQL:%s", line));
                continue;
            }

            try {
                ExplainPlan plan = ExplainPlanParser.parse(jsonTxt);

                StringBuilder build = cmd.toSQL(sqlName, plan);

                IOUtils.write(build, fos);
            } catch (Exception e) {
                System.out.println(line);
                e.printStackTrace();
            }
        }

        fos.flush();
        fos.close();
    }
}