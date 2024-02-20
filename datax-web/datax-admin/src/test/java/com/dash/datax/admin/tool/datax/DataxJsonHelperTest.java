package com.dash.datax.admin.tool.datax;

import com.alibaba.fastjson.JSON;
import com.pji.cloud.datatx.core.enums.DbType;
import com.dash.datax.admin.entity.JobDatasource;
import org.junit.Assert;
import org.junit.Test;

import static com.dash.datax.admin.tool.query.DriverConnectionFactory.buildParameter;

public class DataxJsonHelperTest {

    private JobDatasource getReaderDatasource() {
        JobDatasource readerDatasource = new JobDatasource();
        readerDatasource.setDatasourceName("z01_mysql_3306");
        String parameter = buildParameter("root", "root", DbType.MYSQL, null, "jdbc:mysql://localhost:3306/datax_web?serverTimezone=Asia/Shanghai&useLegacyDatetimeCode=false&useSSL=false&nullNamePatternMatchesAll=true&useUnicode=true&characterEncoding=UTF-8", null, null);
        readerDatasource.setConnectionParams(parameter);
        return readerDatasource;
    }

    private JobDatasource getWriterDatasource() {
        JobDatasource writerDatasource = new JobDatasource();
        writerDatasource.setDatasourceName("z01_mysql_3306");
        String parameter = buildParameter("root", "root", DbType.MYSQL, null, "jdbc:mysql://localhost:3306/datax_web?serverTimezone=Asia/Shanghai&useLegacyDatetimeCode=false&useSSL=false&nullNamePatternMatchesAll=true&useUnicode=true&characterEncoding=UTF-8", null, null);
        writerDatasource.setConnectionParams(parameter);
        return writerDatasource;
    }

    @Test
    public void testBuildCore(){
        DataXJsonHelper jsonHelper = new DataXJsonHelper();
        String result = JSON.toJSONString(jsonHelper.buildCore());
        System.out.println(result);
        Assert.assertTrue(result.contains("byte"));
    }

}
