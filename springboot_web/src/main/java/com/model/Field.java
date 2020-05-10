package com.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("field")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Field extends Model<Field> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "field_id", type = IdType.AUTO)
    private Integer fieldId;

    private Integer fieldType;

    private String fieldTitle;

    @Override
    protected Serializable pkVal() {
        return this.fieldId;
    }

}
