package com.rat.dao;

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
}
