package com.yanggy.cloud.service;

import com.yanggy.cloud.common.utils.ResponseEntityDto;
import com.yanggy.cloud.entity.RabbitMqMessage;
import com.yanggy.cloud.param.RabbitMqMsgParam;

/**
 * @author derrick.yang
 * @Date 9/8/18 15:24
 */
public interface IRabbitMqMsgService {

    ResponseEntityDto<?> getRabbitMsg(RabbitMqMsgParam rabbitMqMsgParam);
    ResponseEntityDto<?> insertRabbitMqMsg(RabbitMqMessage rabbitMqMessage);
}
