package com.yanggy.cloud.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangguiyun on 2017/6/1.
 */

@Data
public class User implements Serializable {
    private long id;
    private String name;
    private String password;
    private int sex;
    private int age;
    private String email;
    private  String phone;
    private List<String> authorities = new ArrayList<>();
}
