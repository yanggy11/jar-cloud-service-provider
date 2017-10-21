package com.yanggy.cloud.api;

import com.yanggy.cloud.dto.Page;
import com.yanggy.cloud.dto.ResponseEntity;
import com.yanggy.cloud.entity.User;
import com.yanggy.cloud.param.UserParam;
import com.yanggy.cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yangguiyun on 2017/6/14.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @RequestMapping(value="/getUserById", method = RequestMethod.POST)
    public ResponseEntity<?> getUserById(@RequestBody UserParam userParam) {
        return new ResponseEntity<>(userService.getUserById(userParam.getUserId()));
    }
    @RequestMapping(value="/userList", method = RequestMethod.POST)
    public Page<?> getUsers(@RequestBody UserParam userParam) {
        return userService.getUserList(userParam);
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
    @RequestMapping(value="/editPassword", method = RequestMethod.POST)
    public ResponseEntity<?> editPassword(@RequestBody UserParam userParam) {
        return userService.editPassword(userParam);
    }
}
