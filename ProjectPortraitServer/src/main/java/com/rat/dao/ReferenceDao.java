package com.rat.dao;

import com.rat.entity.local.File;
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
public interface ReferenceDao {
    @Insert("insert into referenceinfo (userId,referenceedUserId) values (#{userId},#{referenceedUserId})")
    void create(@Param("userId") Long userId, @Param("referenceedUserId") Long referenceedUserId);

    @Delete("delete from referenceinfo where userId=#{userId} and referenceedUserId=#{referenceedUserId}")
    void delete(@Param("userId") Long userId, @Param("referenceedUserId") Long referenceedUserId);

    @Select("select u.* from userinfo u, referenceinfo f where f.userId=#{userId} and f.referenceedUserId = u.userId")
    List<File> findByUserId(@Param("userId") Long userId);

    @Select("select u.* from userinfo u, referenceinfo f where f.referenceedUserId=#{referenceedUserId} and f.userId = u.userId")
    List<File> findByReferenceedUserId(@Param("referenceedUserId") Long referenceedUserId);

    @Select("select count(*) from referenceinfo where userId=#{userId}")
    int findCountByUserId(@Param("userId") Long userId);

    @Select("select count(*) from referenceinfo where referenceedUserId=#{referenceedUserId}")
    int findCountByReferenceedUserId(@Param("referenceedUserId") Long referenceedUserId);

    @Select("select count(*) from referenceinfo where userId=#{userId} and referenceedUserId=#{referenceedUserId}")
    int findCountByUserAndReferenceedUser(@Param("userId") Long userId, @Param("referenceedUserId") Long referenceedUserId);
}
