package com.rat.dao;

import com.rat.entity.local.File;
import com.rat.provider.SqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Dao层
 *
 * @author L.jinzhu on 2018/3/30
 */
@Repository
public interface FileDao {
    @SelectProvider(type = SqlProvider.class, method = "fileFindAll")
    List<File> findAll(@Param("dataIndexStart") int dataIndexStart, @Param("dataIndexEnd") int dataIndexEnd);

    @SelectProvider(type = SqlProvider.class, method = "findBySuffixOrderByLineCount")
    List<File> findBySuffixOrderByLineCount(@Param("suffix") String suffix, @Param("maxLineCount") int maxLineCount, @Param("dataIndexStart") int dataIndexStart, @Param("dataIndexEnd") int dataIndexEnd);

    @Select("select name from file where id=#{fileId} limit 0,1")
    String findNameById(@Param("fileId") Long fileId);

    @Select("select id from file where name=#{name} limit 0,1")
    Long findIdByName(@Param("name") String name);
//
//    @Insert("insert into userinfo (" +
//            "headUrl,nickName,account,accountType,age,sex,bigImg,cityCode,cityName,workCode,workName,educationCode,educationName,houseCode,houseName,marriageCode,marriageName,introduce,remark,token4RongCloud" +
//            ") values (" +
//            "#{file.headUrl},#{file.nickName},#{file.account},#{file.accountType},#{file.age},#{file.sex},#{file.bigImg},#{file.cityCode},#{file.cityName},#{file.workCode},#{file.workName},#{file.educationCode},#{file.educationName},#{file.houseCode},#{file.houseName},#{file.marriageCode},#{file.marriageName},#{file.introduce},#{file.remark},#{file.token4RongCloud}" +
//            ")")
//    @Options(useGeneratedKeys = true, keyProperty = "file.userId")
//    void create(@Param("file") File file);
//
//    @Update("update userinfo set " +
//            "headUrl=#{file.headUrl},nickName=#{file.nickName},age=#{file.age},sex=#{file.sex},bigImg=#{file.bigImg},cityCode=#{file.cityCode}," +
//            "cityName=#{file.cityName},workCode=#{file.workCode},workName=#{file.workName},educationCode=#{file.educationCode},educationName=#{file.educationName},houseCode=#{file.houseCode}," +
//            "houseName=#{file.houseName},marriageCode=#{file.marriageCode},marriageName=#{file.marriageName},introduce=#{file.introduce},remark=#{file.remark}" +
//            " where userId=#{file.userId}")
//    void update(@Param("file") File file);
//
//    @Update("update userinfo set " +
//            "token4RongCloud=#{file.token4RongCloud}" +
//            " where userId=#{file.userId}")
//    void updateToken(@Param("file") File file);
}
