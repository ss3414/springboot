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
@TableName("resource_meta")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ResourceMeta extends Model<ResourceMeta> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "resource_meta_id", type = IdType.AUTO)
    private Integer resourceMetaId;

    private String resourceMetaTitle;

    @Override
    protected Serializable pkVal() {
        return this.resourceMetaId;
    }

}
