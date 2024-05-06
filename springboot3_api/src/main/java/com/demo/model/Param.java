package com.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Param {

    /* springboot默认使用jackson处理JSON */
    @JsonProperty("paramId")
    private Integer id;

    private User user;

}
