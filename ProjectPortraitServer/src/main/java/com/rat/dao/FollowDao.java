package com.rat.dao;

import com.rat.entity.local.user.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Dao层
 *
 * @author L.jinzhu on 2017/3/30
 */
@Repository
public interface FollowDao {
    @Insert("insert into followinfo (userId,followedUserId) values (#{userId},#{followedUserId})")
    void create(@Param("userId") Long userId, @Param("followedUserId") Long followedUserId);

    @Delete("delete from followinfo where userId=#{userId} and followedUserId=#{followedUserId}")
    void delete(@Param("userId") Long userId, @Param("followedUserId") Long followedUserId);

    @Select("select u.* from userinfo u, followinfo f where f.userId=#{userId} and f.followedUserId = u.userId")
    List<User> findByUserId(@Param("userId") Long userId);

    @Select("select u.* from userinfo u, followinfo f where f.followedUserId=#{followedUserId} and f.userId = u.userId")
    List<User> findByFollowedUserId(@Param("followedUserId") Long followedUserId);

    @Select("select count(*) from followinfo where userId=#{userId}")
    int findCountByUserId(@Param("userId") Long userId);

    @Select("select count(*) from followinfo where followedUserId=#{followedUserId}")
    int findCountByFollowedUserId(@Param("followedUserId") Long followedUserId);

    @Select("select count(*) from followinfo where userId=#{userId} and followedUserId=#{followedUserId}")
    int findCountByUserAndFollowedUser(@Param("userId") Long userId, @Param("followedUserId") Long followedUserId);
}
