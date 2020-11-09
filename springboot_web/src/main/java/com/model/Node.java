package com.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("node")
public class Node extends Model<Node> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer parentId;

    private Integer level;

    private String title;

    @TableField(exist = false)
    private List<Node> childrenList;

}
