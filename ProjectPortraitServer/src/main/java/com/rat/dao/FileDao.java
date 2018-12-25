package com.rat.dao;

import com.rat.entity.local.File;
import com.rat.provider.SqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Dao层
 *
 * @author L.jinzhu on 2017/3/30
 */
@Repository
public interface FileDao {
    @Select("select * from userinfo where userId=#{userId} limit 0,1")
    File findById(@Param("userId") Long userId);

    @Select("select * from userinfo where account=#{account} and accountType=#{accountType} limit 0,1")
    File findByAccount(@Param("accountType") int accountType, @Param("account") String account);

    @SelectProvider(type = SqlProvider.class, method = "fileFindAll")
    List<File> findAll(@Param("dataIndexStart") int dataIndexStart, @Param("dataIndexEnd") int dataIndexEnd);

    @Insert("insert into userinfo (" +
            "headUrl,nickName,account,accountType,age,sex,bigImg,cityCode,cityName,workCode,workName,educationCode,educationName,houseCode,houseName,marriageCode,marriageName,introduce,remark,token4RongCloud" +
            ") values (" +
            "#{file.headUrl},#{file.nickName},#{file.account},#{file.accountType},#{file.age},#{file.sex},#{file.bigImg},#{file.cityCode},#{file.cityName},#{file.workCode},#{file.workName},#{file.educationCode},#{file.educationName},#{file.houseCode},#{file.houseName},#{file.marriageCode},#{file.marriageName},#{file.introduce},#{file.remark},#{file.token4RongCloud}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "file.userId")
    void create(@Param("file") File file);

    @Update("update userinfo set " +
            "headUrl=#{file.headUrl},nickName=#{file.nickName},age=#{file.age},sex=#{file.sex},bigImg=#{file.bigImg},cityCode=#{file.cityCode}," +
            "cityName=#{file.cityName},workCode=#{file.workCode},workName=#{file.workName},educationCode=#{file.educationCode},educationName=#{file.educationName},houseCode=#{file.houseCode}," +
            "houseName=#{file.houseName},marriageCode=#{file.marriageCode},marriageName=#{file.marriageName},introduce=#{file.introduce},remark=#{file.remark}" +
            " where userId=#{file.userId}")
    void update(@Param("file") File file);

    @Update("update userinfo set " +
            "token4RongCloud=#{file.token4RongCloud}" +
            " where userId=#{file.userId}")
    void updateToken(@Param("file") File file);
}
