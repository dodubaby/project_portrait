package com.rat.dao;

import com.rat.entity.local.RuleData;
import com.rat.provider.SqlProvider;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
    List<RuleData> findAll(@Param("ruleId") long ruleId, @Param("status") String status);

    /**
     * 查询某种rule count
     *
     * @param group
     * @return
     */
    @Select("select count(*) from rule_data where rule_group = #{group} and status != 'normal'")
    int findRuleCountByGroup(@Param("group") String group);

    @Delete("delete from rule_data where rule_id = #{ruleId}")
    void deleteDataByRuleId(@Param("ruleId") int ruleId);
}
