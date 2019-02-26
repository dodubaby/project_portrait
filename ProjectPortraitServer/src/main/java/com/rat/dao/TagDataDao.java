package com.rat.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Dao层
 *
 * @author L.jinzhu on 2018/3/30
 */
@Repository
public interface TagDataDao {
    @Select("select t.value from tag as t, tag_data as td where td.data_type=#{dataType} and td.data_id=#{dataId} and td.tag_id=t.id and t.type=#{tagType}")
    List<String> findTagListByParam(@Param("tagType") String tagType, @Param("dataType") String dataType, @Param("dataId") long dataId);

    @Select("select td.data_id from tag_data as td, tag as t where t.type=#{tagType} and t.value in (#{tagValues}) and t.id=td.tag_id and td.data_type=#{dataType}")
    List<Long> findDataIdListByTags(@Param("tagType") String tagType, @Param("tagValues") String tagValues, @Param("dataType") String dataType);

    @Select("select td.data_id from tag_data as td, tag as t where t.type=#{tagType} and t.id=td.tag_id and td.data_type=#{dataType}")
    List<Long> findDataIdListByTagType(@Param("tagType") String tagType, @Param("dataType") String dataType);

    @Insert("insert into tag_data (data_type,data_id,tag_id) values (#{dataType},#{dataId},#{tagId})")
    void create(@Param("dataType") String dataType, @Param("dataId") long dataId, @Param("tagId") long tagId);

    @Delete("delete from tag_data where data_type=#{dataType} and data_id=#{dataId}")
    void deleteTagsByDataId(@Param("dataType") String dataType, @Param("dataId") long dataId);

    @Select("select count(*) from tag where type =#{type} and id in (SELECT DISTINCT tag_id FROM tag_data)")
    int findTagCountByType(@Param("type") String type);

    @Delete("delete from tag_data where tag_id=#{tagId}")
    void deleteTagDataByTagId(@Param("tagId") long tagId);

}
