package com.yanggy.cloud.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author derrick.yang
 * @Date 8/28/18 17:49
 */

@Data
public class RabbitMqMessage extends BaseEntity implements Serializable {
    private String messageId;
    private String message;
    private String exchange;
    private String routingKey;
    private int type;
    private int isProcessed;
}
