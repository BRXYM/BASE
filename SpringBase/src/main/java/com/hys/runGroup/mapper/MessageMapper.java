package com.hys.runGroup.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hys.runGroup.domain.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 留言表持久层
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

    /**
     * 根据用户Uid获取留言信息
     */
//    @Select("SELECT hysMEid, hysMEtxt, hysMEtime, hysMEtoid, hysMEidU FROM hysme WHERE hysMEidU = #{uid}")
//    String selectByUid(@Param("uid") int uid);

    /**
     * 根据被评论用户Utoid获取评论信息
     */
//    @Select("SELECT * FROM hysme WHERE hysMEtoid = #{utoid}")
//    String selectByToUid(@Param("utoid") int utoid);

    /**
     * 添加评论信息
     */
    int insert(Message message);

    /**
     * 删除留言信息
     */
    int deleteById(int MEid);

    /**
     * 更新留言信息
     */
    int updateById(Message message);
}
