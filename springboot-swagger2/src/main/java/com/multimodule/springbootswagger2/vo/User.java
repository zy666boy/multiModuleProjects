package com.multimodule.springbootswagger2.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class User {
    private int id;
    private String username;
    private int age;
    private Date ctm;
}
