package com.multimodule.application.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private String name;
    private String sex;
}
