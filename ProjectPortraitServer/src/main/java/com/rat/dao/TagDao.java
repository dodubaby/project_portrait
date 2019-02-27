package com.rat.dao;

import com.rat.entity.local.Tag;
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
public interface TagDao {
    @Select("select * from tag where type=#{type} order by value")
    List<Tag> findByType(@Param("type") String type);

    @Select("select id from tag where value=#{value} limit 1")
    Long findIdByValue(@Param("value") String value);

    @SelectProvider(type = SqlProvider.class, method = "tagFindCountByTagValues")
    int findCountByTagValues(@Param("type") String type, @Param("valueList") String valueList);

    @Insert("insert into tag (type, value) values (#{type}, #{value})")
    void insertTag(@Param("type") String type, @Param("value") String value);

    @Delete("delete from tag where value=#{value} and type=#{type}")
    void deleteTagByValue(@Param("type") String type, @Param("value") String value);

}
