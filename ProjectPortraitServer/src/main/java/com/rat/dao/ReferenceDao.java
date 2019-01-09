package com.rat.dao;

import com.rat.entity.local.File;
import com.rat.entity.local.Reference;
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
public interface ReferenceDao {
    @SelectProvider(type = SqlProvider.class, method = "referenceFindAll")
    List<Reference> findAll(@Param("referenceId") Long referenceId);

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
