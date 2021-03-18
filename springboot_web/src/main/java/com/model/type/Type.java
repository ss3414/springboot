package com.model.type;

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
public class Type {

    private Integer typeMetaId;

    private String typeMetaTitle;

    private Integer fieldId;

    private Integer fieldType;

    private String fieldTitle;

    private Integer typeFieldId;

    private String typeFieldTitle;

    @TableField(exist = false)
    private List<Type> typeList;

}
