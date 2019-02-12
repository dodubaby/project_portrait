package com.rat.dao;

import com.rat.entity.local.Rule;
import com.rat.provider.SqlProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Dao层
 *
 * @author L.jinzhu on 2018/3/30
 */
@Repository
public interface RuleDao {
    @SelectProvider(type = SqlProvider.class, method = "ruleFindAll")
    List<Rule> findAll(@Param("type") String type);
//    @Insert("insert into securitycodeinfo (phoneNumber,code,type,sendTime) values (#{phoneNumber},#{code},#{type},#{sendTime})")
//    void create(@Param("phoneNumber") String phoneNumber, @Param("code") String code, @Param("type") String type, @Param("sendTime") String sendTime);
//
//    @Delete("delete from securitycodeinfo where phoneNumber=#{phoneNumber} and type=#{type}")
//    void delete(@Param("phoneNumber") String phoneNumber, @Param("type") String type);
//
//    @Select("select * from securitycodeinfo where phoneNumber=#{phoneNumber} and type=#{type} and code=#{code} limit 0,1")
//    RuleData find(@Param("phoneNumber") String phoneNumber, @Param("type") String type, @Param("code") String code);
}