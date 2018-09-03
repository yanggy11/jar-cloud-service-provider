package com.yanggy.cloud.api;

import com.yanggy.cloud.common.config.oss.AliOssUtils;
import com.yanggy.cloud.common.service.RedisService;
import com.yanggy.cloud.common.utils.Constants;
import com.yanggy.cloud.dto.Page;
import com.yanggy.cloud.dto.ResponseEntity;
import com.yanggy.cloud.dto.UploadDto;
import com.yanggy.cloud.entity.User;
import com.yanggy.cloud.entity.mongo.MongoTest;
import com.yanggy.cloud.param.UserParam;
import com.yanggy.cloud.repository.mongo.MongoTesstRepository;
import com.yanggy.cloud.service.IUserService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangguiyun on 2017/6/14.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private AmqpTemplate amqpTemplate;
    @RequestMapping(value="/getUserById", method = RequestMethod.POST)
    public ResponseEntity<?> getUserById(@RequestBody UserParam userParam) {
        return new ResponseEntity<>(userService.getUserById(userParam.getUserId()));
    }
    @RequestMapping(value="/userList", method = RequestMethod.POST)
    public Page<List<User>> getUsers(@RequestBody UserParam userParam) {
        Page<List<User>> page = new Page();
        try {
            page = userService.getUserList(userParam);
            amqpTemplate.convertAndSend(Constants.RabbitConstants.USER_EXCHANGE, Constants.RabbitConstants.USER_QUEUE, page);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return page;
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> userLogin(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
        return new ResponseEntity<>(userService.login(user));
    }

    @RequestMapping(value="/updateUserInfo", method = RequestMethod.POST)
    public ResponseEntity<?> updateUserInfo(@RequestBody User user) {
        return new ResponseEntity<>(userService.update(user));
    }
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody User user) {
        return new ResponseEntity<>(userService.register(user));
    }

    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public ResponseEntity<?> deleteUser(@RequestBody UserParam userParam) {
        return userService.deleteUser(userParam.getUserId());
    }
    @DeleteMapping(value="/deleteAll")
    public ResponseEntity<?> deleteBatchUser(@RequestBody UserParam userParam) {
        return userService.deleteBatchUser(userParam.getUserIds());
    }
    @RequestMapping(value="/editPassword", method = RequestMethod.POST)
    public ResponseEntity<?> editPassword(@RequestBody UserParam userParam) {
        return userService.editPassword(userParam);
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
    public Map uploadImage(UploadDto uploadDto, HttpServletRequest request) {

        Map map = new HashMap();

        map.put("imgUrl", aliOssUtils.uploadImage(uploadDto.getFile()));

        return map;
    }
}
