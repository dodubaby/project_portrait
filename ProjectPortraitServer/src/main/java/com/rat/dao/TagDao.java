package com.rat.dao;

import com.rat.entity.local.Tag;
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
public interface TagDao {
    @Select("select * from tag where type=#{type}")
    List<Tag> findByType(@Param("type") String type);
}
