package com.rat.provider;

import com.rat.entity.local.user.User;
import com.rat.utils.StringUtil;

import java.util.List;
import java.util.Map;

/**
 * sql provider
 * Created by liangjinzhu on 17/4/16.
 */
public class SqlProvider {
    public String userFindAll(Map<String, Object> para) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from userinfo where 1=1");

        // 年齡
        if (null != para.get("ageStart") && null != para.get("ageEnd")) {
            int ageStart = (int) para.get("ageStart");
            int ageEnd = (int) para.get("ageEnd");
            if (ageEnd >= ageStart && 0 != ageEnd) {
                sql.append(" and age between " + ageStart + " and " + ageEnd);
            }
        }
        // 性别
        if (null != para.get("sex")) {
            String sex = (String) para.get("sex");
            if (StringUtil.isNotBlank(sex)) {
                sql.append(" and sex = " + sex);
            }
        }
        // 城市
        if (null != para.get("cityCode")) {
            String cityCode = (String) para.get("cityCode");
            if (StringUtil.isNotBlank(cityCode)) {
                sql.append(" and cityCode = " + cityCode);
            }
        }
        // 分页
        if (null != para.get("dataIndexStart") && null != para.get("dataIndexEnd")) {
            int dataIndexStart = (int) para.get("dataIndexStart");
            int dataIndexEnd = (int) para.get("dataIndexEnd");
            sql.append(" limit " + dataIndexStart + "," + dataIndexEnd);
        } else {
            sql.append(" limit 0,0");
        }
        return sql.toString();
    }

    public String videoFindAllByUserList(Map<String, Object> para) {
        StringBuffer sql = new StringBuffer();
        sql.append("select v.* from videoinfo v, user_video uv where 1=1");

        // 拼接用户列表
        if (null != para.get("userList")) {
            List<User> userList = (List<User>) para.get("userList");
            // 存在关注的好友及对应视频
            if (null != userList && 0 != userList.size()) {
                String str = "(";
                for (User user : userList) {
                    str += user.getUserId() + ",";
                }
                str = str.substring(0, str.lastIndexOf(","));
                str += ")";
                sql.append(" and uv.videoId = v.videoId and uv.userId in " + str + " order by v.videoId desc");
            }
            // 不存在关注的好友
            else {
                sql.append(" and 1=2");
            }
        } else {
            sql.append(" and 1=2");
        }
        // 分页
        if (null != para.get("dataIndexStart") && null != para.get("dataIndexEnd")) {
            int dataIndexStart = (int) para.get("dataIndexStart");
            int dataIndexEnd = (int) para.get("dataIndexEnd");
            sql.append(" limit " + dataIndexStart + "," + dataIndexEnd);
        } else {
            sql.append(" limit 0,0");
        }
        return sql.toString();
    }

    public String deleteVideo(Map<String, Object> para) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from videoinfo where 1=1");

        if (null != para.get("videoList")) {
            List<Long> videoList = (List<Long>) para.get("videoList");
            if (null != videoList && 0 != videoList.size()) {
                String str = "(";
                for (Long id : videoList) {
                    str += id + ",";
                }
                str = str.substring(0, str.lastIndexOf(","));
                str += ")";
                sql.append(" and videoId in " + str);
            } else {
                sql.append(" and 1=2");
            }
        } else {
            sql.append(" and 1=2");
        }
        return sql.toString();
    }

    public String deleteUserVideo(Map<String, Object> para) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from user_video where 1=1");

        if (null != para.get("videoList")) {
            List<Long> videoList = (List<Long>) para.get("videoList");
            if (null != videoList && 0 != videoList.size()) {
                String str = "(";
                for (Long id : videoList) {
                    str += id + ",";
                }
                str = str.substring(0, str.lastIndexOf(","));
                str += ")";
                sql.append(" and videoId in " + str);
            } else {
                sql.append(" and 1=2");
            }
        } else {
            sql.append(" and 1=2");
        }
        return sql.toString();
    }
}
