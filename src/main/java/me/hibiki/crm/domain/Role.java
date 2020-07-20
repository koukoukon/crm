package me.hibiki.crm.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author 高弘昆
 * @date 2020/7/16 17:22
 */
@Data
@TableName(value = "`role`")
public class Role implements Serializable {
    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;

    /**
     * 角色名
     */
    @TableField(value = "role_name")
    private String roleName;

    /**
     * 角色描述
     */
    @TableField(value = "role_description")
    private String roleDescription;

    /**
     * 删除标记
     */
    @TableField(value = "role_delete_flag")
    private Integer roleDeleteFlag;

    private static final long serialVersionUID = 1L;
}