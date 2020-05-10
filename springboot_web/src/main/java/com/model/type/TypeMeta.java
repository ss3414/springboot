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
@TableName(value = "type_meta")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TypeMeta extends Model<TypeMeta> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "type_meta_id", type = IdType.AUTO)
    private Integer typeMetaId;

    private String typeMetaTitle;

    @Override
    protected Serializable pkVal() {
        return this.typeMetaId;
    }

}
