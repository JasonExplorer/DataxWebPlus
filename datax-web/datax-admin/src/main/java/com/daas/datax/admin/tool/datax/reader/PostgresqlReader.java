package com.daas.datax.admin.tool.datax.reader;

/**
 * postgresql 构建类
 *
 * @author zhouhongfa@gz-yibo.com
 * @version 1.0
 * @since 2019/8/2
 */
public class PostgresqlReader extends BaseReaderPlugin implements DataxReaderInterface {

    @Override
    public String getName() {
        return "postgresqlreader";
    }
}
