package com.model.type;

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
@TableName("type_field")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TypeField extends Model<TypeField> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "type_field_id", type = IdType.AUTO)
    private Integer typeFieldId;

    private String typeFieldTitle;

    private Integer fieldId;

    private Integer typeMetaId;

    @Override
    protected Serializable pkVal() {
        return this.typeFieldId;
    }

}
