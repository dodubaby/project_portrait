package com.rat.dao;

import com.rat.entity.local.Tag;
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
public interface TagDao {
    @Select("select * from tag where type=#{type} order by value")
    List<Tag> findByType(@Param("type") String type);

    @Select("select id from tag where value=#{value} limit 1")
    Long findIdByValue(@Param("value") String value);

    @Select("select count(*) from tag where type=#{type} and value in (#{valueList})")
    int findCountByTagValues(@Param("type") String type, @Param("valueList") String valueList);

    @Insert("insert into tag (type, value) values (#{type}, #{value})")
    void insertTag(@Param("type") String type, @Param("value") String value);

    @Delete("delete from tag where value=#{value} and type=#{type}")
    void deleteTagByValue(@Param("type") String type, @Param("value") String value);

}
