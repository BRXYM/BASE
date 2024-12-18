package com.hys.runGroup.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hys.runGroup.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 评论表持久层
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    @Select("SELECT * FROM hysc WHERE hysCidMO = #{MOid}")
    List<Comment> selectByMOid(@Param("MOid") int MOid);
}
