package com.daas.datax.admin.mapper;

import com.daas.datax.admin.entity.JobGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author  xuxueli on 16/9/30.
 */
@Mapper
public interface JobGroupMapper {

    List<JobGroup> findAll();

    List<JobGroup> find(@Param("appName") String appName,
                        @Param("title") String title,
                        @Param("addressList") String addressList);

    int save(JobGroup jobGroup);
    List<JobGroup> findByAddressType(@Param("addressType") int addressType);

    int update(JobGroup jobGroup);

    int remove(@Param("id") int id);

    JobGroup load(@Param("id") int id);
}
