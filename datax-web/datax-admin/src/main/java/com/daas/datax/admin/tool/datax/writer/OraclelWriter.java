package com.daas.datax.admin.tool.datax.writer;

/**
 * oracle writer构建类
 *
 * @author zhouhongfa@gz-yibo.com
 * @version 1.0
 * @since 2019/8/2
 */
public class OraclelWriter extends BaseWriterPlugin implements DataxWriterInterface {

    @Override
    public String getName() {
        return "oraclewriter";
    }
}
