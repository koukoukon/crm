package me.hibiki.crm.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 高弘昆
 * @date 2020/7/15 16:58
 */
@Data
@TableName(value = "menu")
public class Menu implements Serializable {
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    @TableField(value = "menu_name")
    private String menuName;

    @TableField(value = "menu_pid")
    private Integer menuPid;

    @TableField(value = "menu_url")
    private String menuUrl;
    @TableField(value = "menu_permission")
    private String menuPermission;
    @TableField(value = "menu_show")
    private Integer menuShow;

    private static final long serialVersionUID = 1L;
}