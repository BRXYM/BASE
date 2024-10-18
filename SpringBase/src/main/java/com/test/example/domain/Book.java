package com.test.example.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("book")
public class Book {
    @TableId(value = "book_id", type = IdType.AUTO)
    private Integer bookId;
    @TableField("book_user_id")
    private Integer bookUserId;
    @TableField("book_classroom_id")
    private Integer bookClassroomId;
    @TableField("book_waiter_id")
    private Integer bookWaiterId;
    @TableField("audit")
    private String audit;
    @TableField("book_start_time")
    private String bookStartTime;
    @TableField("book_end_time")
    private String bookEndTime;

}
