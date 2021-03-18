package com.model.resource;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Resource {

    private Integer resourceMetaId;

    private String resourceMetaTitle;

    private Integer typeFieldId;

    private String typeFieldTitle;

    private Integer resourceFieldId;

    private String resourceFieldContent;

    @TableField(exist = false)
    private List<Resource> resourceList;

}
