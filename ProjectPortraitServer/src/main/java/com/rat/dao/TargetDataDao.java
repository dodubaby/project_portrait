package com.rat.dao;

import com.rat.entity.local.TargetaData;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Dao层
 *
 * @author L.jinzhu on 2017/3/30
 */
@Repository
public interface TargetDataDao {
    @Insert("insert into securitycodeinfo (phoneNumber,code,type,sendTime) values (#{phoneNumber},#{code},#{type},#{sendTime})")
    void create(@Param("phoneNumber") String phoneNumber, @Param("code") String code, @Param("type") String type, @Param("sendTime") String sendTime);

    @Delete("delete from securitycodeinfo where phoneNumber=#{phoneNumber} and type=#{type}")
    void delete(@Param("phoneNumber") String phoneNumber, @Param("type") String type);

    @Select("select * from securitycodeinfo where phoneNumber=#{phoneNumber} and type=#{type} and code=#{code} limit 0,1")
    TargetaData find(@Param("phoneNumber") String phoneNumber, @Param("type") String type, @Param("code") String code);
}
