package com.model.resource;

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
@TableName("resource_field")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ResourceField extends Model<ResourceField> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "resource_field_id", type = IdType.AUTO)
    private Integer resourceFieldId;

    private String resourceFieldContent;

    private Integer typeFieldId;

    private Integer resourceMetaId;

    @Override
    protected Serializable pkVal() {
        return this.resourceFieldId;
    }

}
