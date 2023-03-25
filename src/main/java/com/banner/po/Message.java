package com.banner.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * @author rjj
 * @date 2022/10/13 - 17:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "message")
public class Message {
    @Id
    private ObjectId id;
    @NotEmpty(message = "信息不能为空")
    private String msg;
    //消息状态 1未读 0已读
    @Indexed
    private int status;
    @Field("send_date")
    @Indexed
    private String sendDate;
    @Field("read_date")
    private String readDate;
    @Indexed
    private Long from;
    @Indexed
    private Long to;
}
