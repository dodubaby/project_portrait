package com.rat.dao;

import com.rat.entity.local.Rule;
import com.rat.provider.SqlProvider;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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
    List<Rule> findAll(@Param("ruleGroup") String ruleGroup);

    @Delete("delete from rule where regular=#{regular}")
    void deleteRuleByRegular(@Param("regular") String regular);

    @Delete("select id from rule where regular=#{regular}")
    int selectIdByRegular(@Param("regular") String regular);

    @Insert("insert into rule(regular, scan_file_suffix, key_left, key_right, rule_group, remark, creater, create_time) " +
            "values (#{regular}, #{scanFileSuffix}, #{keyLeft}, #{keyRight}, #{ruleGroup}, #{remark}, #{creater}, #{createTime})")
    void insertRule(@Param("regular") String regular, @Param("scanFileSuffix") String scanFileSuffix,
                    @Param("keyLeft") String keyLeft, @Param("keyRight") String keyRight,
                    @Param("ruleGroup") String ruleGroup, @Param("remark") String remark,
                    @Param("creater") String creater, @Param("createTime") String createTime);

//    @Insert("insert into securitycodeinfo (phoneNumber,code,type,sendTime) values (#{phoneNumber},#{code},#{type},#{sendTime})")
//    void create(@Param("phoneNumber") String phoneNumber, @Param("code") String code, @Param("type") String type, @Param("sendTime") String sendTime);
//
//    @Delete("delete from securitycodeinfo where phoneNumber=#{phoneNumber} and type=#{type}")
//    void delete(@Param("phoneNumber") String phoneNumber, @Param("type") String type);
//
//    @Select("select * from securitycodeinfo where phoneNumber=#{phoneNumber} and type=#{type} and code=#{code} limit 0,1")
//    RuleData find(@Param("phoneNumber") String phoneNumber, @Param("type") String type, @Param("code") String code);
}
