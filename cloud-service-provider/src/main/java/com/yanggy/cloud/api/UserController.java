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
    @RequestMapping(value="/getUserById", method = RequestMethod.GET)
    public ResponseEntity<?> getUserById(long id) {
        return new ResponseEntity<>(userService.getUserById(id));
    }
    @RequestMapping(value="/userList", method = RequestMethod.POST)
    public Page<?> getUsers(@RequestBody UserParam userParam) {
        return userService.getUserList(userParam);
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> userLogin(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
        return new ResponseEntity<>(userService.login(user));
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody User user) {
        userService.register(user);
        return new ResponseEntity<>(null);
    }
}
