package com.yanggy.cloud.api;

import com.yanggy.cloud.entity.User;
import com.yanggy.cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangguiyun on 2017/6/14.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @RequestMapping(value="/getUserById", method = RequestMethod.GET)
    public User getUserById(long id) {
        return userService.getUserById(id);
    }
    @RequestMapping(value="/userList", method = RequestMethod.POST)
    public Object getUsers() {
        return userService.getUserList();
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String,Object> userLogin(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
        Map<String, Object> map = new HashMap<String,Object>();
        User userLogin = userService.login(user);
        map.put("id",userLogin == null ? "" : userLogin.getId());
        map.put("name",userLogin == null ? "" : userLogin.getName());
        map.put("age",userLogin == null ? "" : userLogin.getAge());
        map.put("sex",userLogin == null ? "" : userLogin.getSex() == 0 ? "男" : "女");

        return map;
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public Map<String, Object> register(@RequestBody User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            userService.register(user);
            map.put("message","success");
            map.put("code","200");
        }catch (Exception e) {
            map.put("message",e.getMessage());
            map.put("code","400");
        }
        return map;
    }
}
