package com.daas.datax.admin.tool.datax.reader;


/**
 * mysql reader 构建类
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName MysqlReader
 * @Version 1.0
 * @since 2019/7/30 23:07
 */
public class MysqlReader extends BaseReaderPlugin implements DataxReaderInterface {

    @Override
    public String getName() {
        return "mysqlreader";
    }
}
