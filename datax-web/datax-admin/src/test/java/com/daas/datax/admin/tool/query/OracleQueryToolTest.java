package com.daas.datax.admin.tool.query;

import com.daas.datatx.core.enums.DbType;
import com.daas.datax.admin.entity.JobDatasource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.daas.datax.admin.tool.query.DriverConnectionFactory.buildParameter;

@Slf4j
public class OracleQueryToolTest {

    private BaseQueryTool queryTool;
    private JobDatasource jdbcDatasource;

    @Before
    public void before() {
        genMysqlDemo();
        queryTool = QueryToolFactory.getByDbType(jdbcDatasource.getType(),jdbcDatasource.getConnectionParams());
    }

    private void genMysqlDemo() {
        jdbcDatasource = new JobDatasource();
        jdbcDatasource.setDatasourceName("test");
        String parameter = buildParameter("scott", "tiger", DbType.ORACLE, null, "jdbc:oracle:thin:@localhost:1521/orcl", null, null);
        jdbcDatasource.setConnectionParams(parameter);
    }

    @Test
    public void getTableNames() {
        List<String> tableNames = queryTool.getTableNames(null);
        tableNames.forEach(System.out::println);
    }

    @Test
    public void getColumnNames() {
        List<String> columns = queryTool.getColumnNames("EMP",jdbcDatasource.getType());
        log.info(columns.toString());
    }
}