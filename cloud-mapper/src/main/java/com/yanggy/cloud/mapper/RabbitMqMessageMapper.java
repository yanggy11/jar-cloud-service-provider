package com.yanggy.cloud.mapper;

import com.yanggy.cloud.entity.RabbitMqMessage;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author derrick.yang
 * @Date 8/28/18 17:52
 */

@Mapper
public interface RabbitMqMessageMapper {
    int addMessage(RabbitMqMessage rabbitMqMessage);
}
