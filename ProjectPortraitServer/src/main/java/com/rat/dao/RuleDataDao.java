package com.rat.dao;

import com.rat.entity.local.RuleData;
import com.rat.provider.SqlProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Dao层
 *
 * @author L.jinzhu on 2018/3/30
 */
@Repository
public interface RuleDataDao {
    @SelectProvider(type = SqlProvider.class, method = "ruleDataFindAll")
    List<RuleData> findAll(@Param("status") String status, @Param("ruleId") long ruleId);
}
