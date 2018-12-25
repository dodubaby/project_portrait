package com.rat.dao;

import com.rat.entity.local.ResourceData;
import com.rat.entity.local.File;
import com.rat.provider.SqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * dao层
 *
 * @author L.jinzhu on 2017/3/30
 */
@Repository
public interface ResourceDao {
    @Select("select v.* from resourceinfo v, user_resource uv where uv.userId=#{userId} and uv.resourceId = v.resourceId")
    List<ResourceData> findByUser(@Param("userId") Long userId);

    @Select("select v.* from resourceinfo v, userinfo u, user_resource uv where u.sex != #{sex} and u.userId = uv.userId and uv.resourceId = v.resourceId order by v.resourceId desc limit 0,5")
    List<ResourceData> findCount5BySex(@Param("sex") String sex);

    @Select("select v.* from resourceinfo v, user_resource uv where uv.userId=#{userId} and uv.isDefault = 1 and uv.resourceId = v.resourceId limit 0,1")
    ResourceData findDefault(@Param("userId") Long userId);

    @Select("select count(*) from user_resource where userId=#{userId}")
    int findUserResourceCount(@Param("userId") Long userId);

    @Select("select * from user_resource where userId=#{userId}")
    List<ResourceData> findUserResourceByUser(@Param("userId") Long userId);

    @SelectProvider(type = SqlProvider.class, method = "resourceFindAllByUserList")
    List<ResourceData> findAllByUserList(@Param("dataIndexStart") int dataIndexStart, @Param("dataIndexEnd") int dataIndexEnd, @Param("fileList") List<File> fileList);

    @Insert("insert into resourceinfo (" +
            "resourceTitle,resourceTime,resourceImg,resourceUrl,resourcePlayTime,userId,remark" +
            ") values (" +
            "#{resourceData.resourceTitle},#{resourceData.resourceTime},#{resourceData.resourceImg},#{resourceData.resourceUrl},#{resourceData.resourcePlayTime},#{resourceData.userId},#{resourceData.remark}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "resourceData.resourceId")
    void create(@Param("resourceData") ResourceData resourceData);

    @Insert("insert into user_resource (resourceId,userId,isDefault) values (#{resourceId},#{userId},#{isDefault})")
    void createUserResource(@Param("resourceId") Long resourceId, @Param("userId") Long userId, @Param("isDefault") String isDefault);

    @Update("update user_resource set isDefault = 1 where userId=#{userId} and resourceId=#{resourceId}")
    void updateUserResourceSetDefault(@Param("userId") Long userId, @Param("resourceId") Long resourceId);

    @Update("update user_resource set isDefault = 0 where userId=#{userId}")
    void updateUserResourceSetAllNotDefault(@Param("userId") Long userId);

    @DeleteProvider(type = SqlProvider.class, method = "deleteResource")
    int deleteResource(@Param("resourceList") List<Long> resourceList);

    @DeleteProvider(type = SqlProvider.class, method = "deleteUserResource")
    int deleteUserResource(@Param("resourceList") List<Long> resourceList);
}
