package com.demo.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Form {

    private String name;

    private List<String> pwdList;

}
