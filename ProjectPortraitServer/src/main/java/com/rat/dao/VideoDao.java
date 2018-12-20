package com.rat.dao;

import com.rat.entity.local.user.User;
import com.rat.entity.local.video.Video;
import com.rat.provider.SqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * dao层
 *
 * @author L.jinzhu on 2017/3/30
 */
@Repository
public interface VideoDao {
    @Select("select v.* from videoinfo v, user_video uv where uv.userId=#{userId} and uv.videoId = v.videoId")
    List<Video> findByUser(@Param("userId") Long userId);

    @Select("select v.* from videoinfo v, userinfo u, user_video uv where u.sex != #{sex} and u.userId = uv.userId and uv.videoId = v.videoId order by v.videoId desc limit 0,5")
    List<Video> findCount5BySex(@Param("sex") String sex);

    @Select("select v.* from videoinfo v, user_video uv where uv.userId=#{userId} and uv.isDefault = 1 and uv.videoId = v.videoId limit 0,1")
    Video findDefault(@Param("userId") Long userId);

    @Select("select count(*) from user_video where userId=#{userId}")
    int findUserVideoCount(@Param("userId") Long userId);

    @Select("select * from user_video where userId=#{userId}")
    List<Video> findUserVideoByUser(@Param("userId") Long userId);

    @SelectProvider(type = SqlProvider.class, method = "videoFindAllByUserList")
    List<Video> findAllByUserList(@Param("dataIndexStart") int dataIndexStart, @Param("dataIndexEnd") int dataIndexEnd, @Param("userList") List<User> userList);

    @Insert("insert into videoinfo (" +
            "videoTitle,videoTime,videoImg,videoUrl,videoPlayTime,userId,remark" +
            ") values (" +
            "#{video.videoTitle},#{video.videoTime},#{video.videoImg},#{video.videoUrl},#{video.videoPlayTime},#{video.userId},#{video.remark}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "video.videoId")
    void create(@Param("video") Video video);

    @Insert("insert into user_video (videoId,userId,isDefault) values (#{videoId},#{userId},#{isDefault})")
    void createUserVideo(@Param("videoId") Long videoId, @Param("userId") Long userId, @Param("isDefault") String isDefault);

    @Update("update user_video set isDefault = 1 where userId=#{userId} and videoId=#{videoId}")
    void updateUserVideoSetDefault(@Param("userId") Long userId, @Param("videoId") Long videoId);

    @Update("update user_video set isDefault = 0 where userId=#{userId}")
    void updateUserVideoSetAllNotDefault(@Param("userId") Long userId);

    @DeleteProvider(type = SqlProvider.class, method = "deleteVideo")
    int deleteVideo(@Param("videoList") List<Long> videoList);

    @DeleteProvider(type = SqlProvider.class, method = "deleteUserVideo")
    int deleteUserVideo(@Param("videoList") List<Long> videoList);
}
