package com.yanggy.cloud.api;

import com.alibaba.fastjson.JSONObject;
import com.yanggy.cloud.common.config.oss.AliOssUtils;
import com.yanggy.cloud.common.config.rabbit.SendMq;
import com.yanggy.cloud.common.enums.ErrorCode;
import com.yanggy.cloud.common.service.RedisService;
import com.yanggy.cloud.common.utils.Constants;
import com.yanggy.cloud.common.utils.ResponseEntityBuilder;
import com.yanggy.cloud.common.utils.ResponseEntityDto;
import com.yanggy.cloud.dto.Page;
import com.yanggy.cloud.dto.UploadDto;
import com.yanggy.cloud.entity.RabbitMqMessage;
import com.yanggy.cloud.entity.User;
import com.yanggy.cloud.entity.mongo.MongoTest;
import com.yanggy.cloud.param.UserParam;
import com.yanggy.cloud.repository.mongo.MongoTesstRepository;
import com.yanggy.cloud.service.IFileUploadService;
import com.yanggy.cloud.service.IRabbitMqMsgService;
import com.yanggy.cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 * Created by yangguiyun on 2017/6/14.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IFileUploadService fileUploadService;

    @Autowired
    private IRabbitMqMsgService rabbitMqMsgService;

    @Autowired
    private SendMq sendMq;
    @RequestMapping(value="/getUserById", method = RequestMethod.POST)
    public ResponseEntityDto<?> getUserById(@RequestBody UserParam userParam) {
        ResponseEntityDto<?> res = null;
        try {
            res = ResponseEntityBuilder.buildNormalResponseEntity(userService.getUserById(userParam.getUserId()));
        }catch (Exception e) {
            res = ResponseEntityBuilder.buildErrorResponseEntity(ErrorCode.UNKONWN_ERROR);
        }

        return res;
    }
    @RequestMapping(value="/userList", method = RequestMethod.POST)
    public Page<List<User>> getUsers(@RequestBody UserParam userParam) {
        Page<List<User>> page = new Page();
        try {
            page = userService.getUserList(userParam);

            this.addMessage(page);

            sendMq.sendMq(Constants.RabbitConstants.USER_EXCHANGE, Constants.RabbitConstants.USER_QUEUE, page);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return page;
    }

    private void addMessage(Page<List<User>> page) {
        RabbitMqMessage rabbitMqMessage = new RabbitMqMessage();
        rabbitMqMessage.setType(1);
        rabbitMqMessage.setMessage(JSONObject.toJSONString(page));
        rabbitMqMessage.setRoutingKey(Constants.RabbitConstants.USER_QUEUE);
        rabbitMqMessage.setExchange(Constants.RabbitConstants.USER_EXCHANGE);

        rabbitMqMessage.setIsProcessed(0);
        String messageId = UUID.randomUUID().toString().replace("-","");
        rabbitMqMessage.setMessageId(messageId);

        rabbitMqMsgService.insertRabbitMqMsg(rabbitMqMessage);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntityDto<?> userLogin(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
        ResponseEntityDto<?> res = null;

        try {
            res = ResponseEntityBuilder.buildNormalResponseEntity(userService.login(user));
        }catch (Exception e) {
            res = ResponseEntityBuilder.buildErrorResponseEntity(ErrorCode.UNKONWN_ERROR);
        }

        return res;
    }

    @RequestMapping(value="/updateUserInfo", method = RequestMethod.POST)
    public ResponseEntityDto<?> updateUserInfo(@RequestBody User user) {
        ResponseEntityDto<?> res = null;

        try {
            userService.update(user);

            res = ResponseEntityBuilder.buildNormalResponseEntity();
        }catch (Exception e) {
            res = ResponseEntityBuilder.buildErrorResponseEntity(ErrorCode.UNKONWN_ERROR);
        }

        return res;
    }
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ResponseEntityDto<?> register(@RequestBody User user) {
        ResponseEntityDto<?> res = null;

        try {
            res = ResponseEntityBuilder.buildNormalResponseEntity(userService.register(user));
        }catch (Exception e) {
            res= ResponseEntityBuilder.buildErrorResponseEntity(ErrorCode.UNKONWN_ERROR);
        }

        return res;
    }

    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public ResponseEntityDto<?> deleteUser(@RequestBody UserParam userParam) {
        ResponseEntityDto<?> res = null;

        try {
            userService.deleteUser(userParam.getUserId());

            res = ResponseEntityBuilder.buildNormalResponseEntity();
        }catch (Exception e) {
            res = ResponseEntityBuilder.buildErrorResponseEntity(ErrorCode.UNKONWN_ERROR);
        }

        return res;
    }
    @DeleteMapping(value="/deleteAll")
    public ResponseEntityDto<?> deleteBatchUser(@RequestBody UserParam userParam) {
        ResponseEntityDto<?> res = null;

        try {
            userService.deleteBatchUser(userParam.getUserIds());

            res = ResponseEntityBuilder.buildNormalResponseEntity();
        }catch (Exception e) {
            res = ResponseEntityBuilder.buildErrorResponseEntity(ErrorCode.UNKONWN_ERROR);
        }

        return res;
    }
    @RequestMapping(value="/editPassword", method = RequestMethod.POST)
    public ResponseEntityDto<?> editPassword(@RequestBody UserParam userParam) {
        ResponseEntityDto<?> res = null;

        try {
            userService.editPassword(userParam);
            res = ResponseEntityBuilder.buildNormalResponseEntity();
        }catch (Exception e) {
            res = ResponseEntityBuilder.buildErrorResponseEntity(ErrorCode.UNKONWN_ERROR);
        }

        return res;
    }

    @Autowired
    private MongoTesstRepository mongoTesstRepository;

    @Autowired
    private RedisService redisService;

    @PostMapping(value = "/mongo")
    public String mongoTest() {
        MongoTest mongoTest = new MongoTest();

        mongoTest.setName("derrick.test");
        redisService.set("test_sentinal", "test_sentinal");
        try {
            mongoTesstRepository.save(mongoTest);

            System.out.println(mongoTesstRepository.findAll());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "1";
    }

    @Autowired
    private AliOssUtils aliOssUtils;
    @RequestMapping(value="/uploa", method = RequestMethod.POST)
    public ResponseEntityDto<?> uploadImage(UploadDto uploadDto, HttpServletRequest request) {

        ResponseEntityDto<?> res = null;

        try{
            res = ResponseEntityBuilder.buildNormalResponseEntity(fileUploadService.fileUpload(uploadDto.getFile()));
        }catch (Exception e) {
            res = ResponseEntityBuilder.buildErrorResponseEntity(ErrorCode.UNKONWN_ERROR);
        }

        return res;
    }
}
