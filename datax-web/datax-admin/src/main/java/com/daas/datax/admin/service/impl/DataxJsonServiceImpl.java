package com.daas.datax.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.daas.datax.admin.dto.DataXJsonBuildDTO;
import com.daas.datax.admin.entity.JobDatasource;
import com.daas.datax.admin.service.DataxJsonService;
import com.daas.datax.admin.service.JobDatasourceService;
import com.daas.datax.admin.tool.datax.DataXJsonHelper;
import com.daas.datax.admin.tool.table.TableNameHandle;
import com.daas.datatx.core.enums.DbType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * com.wugui.datax json构建实现类
 *
 * @author weiye
 * @ClassName DataxJsonServiceImpl
 * @Version 2.0
 * @since 2020/01/11 17:15
 */
@Service
public class DataxJsonServiceImpl implements DataxJsonService {

    @Autowired
    private JobDatasourceService jobDataSourceService;

    @Override
    public String buildJobJson(DataXJsonBuildDTO dataXJsonBuildDto) {
        DataXJsonHelper dataxJsonHelper = new DataXJsonHelper();
      
        // reader
        JobDatasource readerDatasource = jobDataSourceService.getById(dataXJsonBuildDto.getReaderDatasourceId());
      
        dataxJsonHelper.initTransformer(dataXJsonBuildDto);
      
        //处理reader表名
        processingTableName(readerDatasource, dataXJsonBuildDto.getReaderTables());
        // reader plugin init
        dataxJsonHelper.initReader(dataXJsonBuildDto, readerDatasource);

        JobDatasource writerDatasource = jobDataSourceService.getById(dataXJsonBuildDto.getWriterDatasourceId());
        //处理writer表名
        processingTableName(writerDatasource, dataXJsonBuildDto.getWriterTables());

        dataxJsonHelper.initWriter(dataXJsonBuildDto, writerDatasource);

        return JSON.toJSONString(dataxJsonHelper.buildJob());
    }

    /**
     * 处理表名称
     * 解决生成json中的表名称大小写敏感问题
     * 目前针对Oracle和postgreSQL
     * @param jobDatasource
     * @param tables
     */
    private void processingTableName(JobDatasource jobDatasource, List<String> tables) {
        if (DbType.ORACLE.equals(jobDatasource.getType()) || DbType.POSTGRESQL.equals(jobDatasource.getType())) {
            for (int i = 0; i < tables.size(); i++) {
                tables.set(i, TableNameHandle.addDoubleQuotes(tables.get(i)));
            }
        }
    }


}