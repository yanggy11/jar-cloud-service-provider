package com.yanggy.cloud.rabbitmq;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractJsonMessageConverter;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: yangguiyun
 * @Date: 2017/10/31 14:25
 * @Description:
 */

//@Component("objectMessageConverter")
public class ObjectMessageConverter extends AbstractJsonMessageConverter {

    private Logger log = LoggerFactory.getLogger(getClass());

    private ObjectMapper jsonObjectMapper = new ObjectMapper();

    private Jackson2JavaTypeMapper javaTypeMapper = new DefaultJackson2JavaTypeMapper();

    public ObjectMessageConverter() {
        super();
        initializeJsonObjectMapper();
    }

    public Jackson2JavaTypeMapper getJavaTypeMapper() {
        return javaTypeMapper;
    }

    public void setJavaTypeMapper(Jackson2JavaTypeMapper javaTypeMapper) {
        this.javaTypeMapper = javaTypeMapper;
    }

    /**
     * The {@link ObjectMapper} to use instead of using the default. An
     * alternative to injecting a mapper is to extend this class and override
     * {@link #initializeJsonObjectMapper()}.
     *
     * @param jsonObjectMapper the object mapper to set
     */
    public void setJsonObjectMapper(ObjectMapper jsonObjectMapper) {
        this.jsonObjectMapper = jsonObjectMapper;
    }

    /**
     * Subclass and override to customize.
     */
    protected void initializeJsonObjectMapper() {
        jsonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public Object fromMessage(Message message)
            throws MessageConversionException {
        Object content = null;
        MessageProperties properties = message.getMessageProperties();
        if (properties != null) {
            String contentType = properties.getContentType();
            Object body = message.getBody();
            if (body instanceof byte[]) {
                content = new String((byte[]) body);
            }
            log.warn("Could not convert incoming message with content-type ["
                    + contentType + "]");
        }
        if (content == null) {
            content = message.getBody();
        }
        return content;
    }

//	private Object convertBytesToObject(byte[] body, String encoding,
//			JavaType targetJavaType) throws JsonParseException,
//			JsonMappingException, IOException {
//		String contentAsString = new String(body, encoding);
//		return jsonObjectMapper.readValue(contentAsString, targetJavaType);
//	}
//
//	private Object convertBytesToObject(byte[] body, String encoding,
//			Class<?> targetClass) throws JsonParseException,
//			JsonMappingException, IOException {
//		String contentAsString = new String(body, encoding);
//		return jsonObjectMapper.readValue(contentAsString, jsonObjectMapper.constructType(targetClass));
//	}

    @Override
    protected Message createMessage(Object objectToConvert,
                                    MessageProperties messageProperties)
            throws MessageConversionException {
        byte[] bytes = null;
        try {
            String jsonString = jsonObjectMapper
                    .writeValueAsString(objectToConvert);
            bytes = jsonString.getBytes(getDefaultCharset());
        } catch (IOException e) {
            throw new MessageConversionException(
                    "Failed to convert Message content", e);
        }
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        messageProperties.setContentEncoding(getDefaultCharset());
        if (bytes != null) {
            messageProperties.setContentLength(bytes.length);
        }

        if (getClassMapper() == null) {
            getJavaTypeMapper().fromJavaType(jsonObjectMapper.constructType(objectToConvert.getClass()),
                    messageProperties);

        } else {
            getClassMapper().fromClass(objectToConvert.getClass(),
                    messageProperties);

        }

        return new Message(bytes, messageProperties);

    }

}
