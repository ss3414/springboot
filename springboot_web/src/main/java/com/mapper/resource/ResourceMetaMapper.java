package com.mapper.resource;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.model.resource.Resource;
import com.model.resource.ResourceMeta;
import org.apache.ibatis.annotations.Select;

public interface ResourceMetaMapper extends BaseMapper<ResourceMeta> {

    @Select({
            "<script>",
            "SELECT a.*,c.type_field_id,c.type_field_title,b.resource_field_id,b.resource_field_content",
            "FROM `resource_meta` a,`resource_field` b,`type_field` c",
            "WHERE a.resource_meta_id = b.resource_meta_id AND b.type_field_id = c.type_field_id",
            "<when test='resourceMeta.resourceMetaId != null'>",
            "AND a.resource_meta_id = #{resourceMeta.resourceMetaId}",
            "</when>",
            "</script>",
    })
    IPage<Resource> selectResourceList(Page page, ResourceMeta resourceMeta);

}
