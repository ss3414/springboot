package com.mapper.type;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.model.type.Type;
import com.model.type.TypeMeta;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeMetaMapper extends BaseMapper<TypeMeta> {

    @Select({
            "<script>",
            "SELECT a.*,c.*,b.type_field_id,b.type_field_title",
            "FROM `type_meta` a,`type_field` b,`field` c",
            "WHERE a.type_meta_id = b.type_meta_id AND b.field_id = c.field_id",
            "<when test='typeMeta.typeMetaId != null'>",
            "AND a.type_meta_id = #{typeMeta.typeMetaId}",
            "</when>",
            "</script>",
    })
    IPage<Type> selectTypeList(Page page, TypeMeta typeMeta);

}
