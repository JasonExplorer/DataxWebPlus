package com.daas.datax.admin.tool.datax;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象实现类
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName BaseDataxPlugin
 * @Version 1.0
 * @since 2019/7/31 9:45
 */
public abstract class BaseDataXPlugin implements DataXPluginInterface {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

}
