package com.rat.dao;

import com.rat.entity.local.user.User;
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
public interface UserDao {
    @Select("select * from userinfo where userId=#{userId} limit 0,1")
    User findById(@Param("userId") Long userId);

    @Select("select * from userinfo where account=#{account} and accountType=#{accountType} limit 0,1")
    User findByAccount(@Param("accountType") int accountType, @Param("account") String account);

    @SelectProvider(type = SqlProvider.class, method = "userFindAll")
    List<User> findAll(@Param("dataIndexStart") int dataIndexStart, @Param("dataIndexEnd") int dataIndexEnd, @Param("ageStart") int ageStart, @Param("ageEnd") int ageEnd, @Param("sex") String sex, @Param("cityCode") String cityCode);

    @Insert("insert into userinfo (" +
            "headUrl,nickName,account,accountType,age,sex,bigImg,cityCode,cityName,workCode,workName,educationCode,educationName,houseCode,houseName,marriageCode,marriageName,introduce,remark,token4RongCloud" +
            ") values (" +
            "#{user.headUrl},#{user.nickName},#{user.account},#{user.accountType},#{user.age},#{user.sex},#{user.bigImg},#{user.cityCode},#{user.cityName},#{user.workCode},#{user.workName},#{user.educationCode},#{user.educationName},#{user.houseCode},#{user.houseName},#{user.marriageCode},#{user.marriageName},#{user.introduce},#{user.remark},#{user.token4RongCloud}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "user.userId")
    void create(@Param("user") User user);

    @Update("update userinfo set " +
            "headUrl=#{user.headUrl},nickName=#{user.nickName},age=#{user.age},sex=#{user.sex},bigImg=#{user.bigImg},cityCode=#{user.cityCode}," +
            "cityName=#{user.cityName},workCode=#{user.workCode},workName=#{user.workName},educationCode=#{user.educationCode},educationName=#{user.educationName},houseCode=#{user.houseCode}," +
            "houseName=#{user.houseName},marriageCode=#{user.marriageCode},marriageName=#{user.marriageName},introduce=#{user.introduce},remark=#{user.remark}" +
            " where userId=#{user.userId}")
    void update(@Param("user") User user);

    @Update("update userinfo set " +
            "token4RongCloud=#{user.token4RongCloud}" +
            " where userId=#{user.userId}")
    void updateToken(@Param("user") User user);
}
