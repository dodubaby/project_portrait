package com.rat.provider;

import com.rat.utils.SafeParseUtils;

import java.util.List;
import java.util.Map;

/**
 * sql provider
 * Created by liangjinzhu on 17/4/16.
 */
public class SqlProvider {
    public String fileFindAll(Map<String, Object> para) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from file where 1=1");
        // 分页
        sql.append(getCountLimitCondition(para));
        return sql.toString();
    }

    public String findBySuffixOrderByLineCount(Map<String, Object> para) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from file where 1=1");
        // 文件后缀
        if (null != para.get("suffix")) {
            String suffix = (String) para.get("suffix");
            sql.append(" and suffix = '" + suffix + "'");
        }
        // 最大行数
        int maxLineCount = 0;
        if (null != para.get("maxLineCount")) {
            maxLineCount = (int) para.get("maxLineCount");
        }
        maxLineCount = maxLineCount < 400 ? 400 : maxLineCount;
        sql.append(" and line_count >= " + maxLineCount);

        sql.append(" order by line_count desc");
        // 分页
        sql.append(getCountLimitCondition(para));
        return sql.toString();
    }

    public String resourceFindAll(Map<String, Object> para) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from resource where 1=1");
        // 分页
        sql.append(getCountLimitCondition(para));
        return sql.toString();
    }

    public String resourceFindByValue(Map<String, Object> para) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from resource where 1=1");
        // 文件后缀
        if (null != para.get("value")) {
            String value = (String) para.get("value");
            sql.append(" and resource_value like '%" + value + "%'");
        }
        return sql.toString();
    }

    public String targetDataFindAll(Map<String, Object> para) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from target_data where 1=1");
        // 分页
        sql.append(getCountLimitCondition(para));
        return sql.toString();
    }

    public String referenceFindAll(Map<String, Object> para) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from reference where 1=1 and reference_data_type ='java'");

        // 被引用的数据
        if (null != para.get("referenceId")) {
            Long referenceId = SafeParseUtils.parseLong(para.get("referenceId").toString());
            if (0 != referenceId) {
                sql.append(" and reference_data_id = " + referenceId);
            } else {
                sql.append(" limit 1000");// 强制分页
            }
        } else {
            sql.append(" limit 1000");// 强制分页
        }
        return sql.toString();
    }

    public String deleteResource(Map<String, Object> para) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from resourceinfo where 1=1");

        if (null != para.get("resourceList")) {
            List<Long> resourceList = (List<Long>) para.get("resourceList");
            if (null != resourceList && 0 != resourceList.size()) {
                String str = "(";
                for (Long id : resourceList) {
                    str += id + ",";
                }
                str = str.substring(0, str.lastIndexOf(","));
                str += ")";
                sql.append(" and resourceId in " + str);
            } else {
                sql.append(" and 1=2");
            }
        } else {
            sql.append(" and 1=2");
        }
        return sql.toString();
    }

    public String deleteUserResource(Map<String, Object> para) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from user_resource where 1=1");

        if (null != para.get("resourceList")) {
            List<Long> resourceList = (List<Long>) para.get("resourceList");
            if (null != resourceList && 0 != resourceList.size()) {
                String str = "(";
                for (Long id : resourceList) {
                    str += id + ",";
                }
                str = str.substring(0, str.lastIndexOf(","));
                str += ")";
                sql.append(" and resourceId in " + str);
            } else {
                sql.append(" and 1=2");
            }
        } else {
            sql.append(" and 1=2");
        }
        return sql.toString();
    }


    private String getCountLimitCondition(Map<String, Object> para) {
        String countLimit;
        if (null != para.get("dataIndexStart") && null != para.get("dataIndexEnd")) {
            int dataIndexStart = (int) para.get("dataIndexStart");
            int dataIndexEnd = (int) para.get("dataIndexEnd");
            countLimit = " limit " + dataIndexStart + "," + dataIndexEnd;
        } else {
            countLimit = " limit 0,0";
        }
        return countLimit;
    }
}
