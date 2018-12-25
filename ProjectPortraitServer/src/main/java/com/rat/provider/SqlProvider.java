package com.rat.provider;

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

//        // 年齡
//        if (null != para.get("ageStart") && null != para.get("ageEnd")) {
//            int ageStart = (int) para.get("ageStart");
//            int ageEnd = (int) para.get("ageEnd");
//            if (ageEnd >= ageStart && 0 != ageEnd) {
//                sql.append(" and age between " + ageStart + " and " + ageEnd);
//            }
//        }
//        // 性别
//        if (null != para.get("sex")) {
//            String sex = (String) para.get("sex");
//            if (StringUtil.isNotBlank(sex)) {
//                sql.append(" and sex = " + sex);
//            }
//        }
//        // 城市
//        if (null != para.get("cityCode")) {
//            String cityCode = (String) para.get("cityCode");
//            if (StringUtil.isNotBlank(cityCode)) {
//                sql.append(" and cityCode = " + cityCode);
//            }
//        }
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

    public String targetDataFindAll(Map<String, Object> para) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from target_data where 1=1");
        // 分页
        sql.append(getCountLimitCondition(para));
        return sql.toString();
    }

    public String referenceFindAll(Map<String, Object> para) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from reference where 1=1");
        // 分页
        sql.append(getCountLimitCondition(para));
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
