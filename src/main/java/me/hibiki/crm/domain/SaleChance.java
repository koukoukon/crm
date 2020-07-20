package me.hibiki.crm.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 高弘昆
 * @date 2020/7/17 21:53
 */
@Data
@TableName(value = "t_sale_chance")
public class SaleChance implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "chanceSource")
    private String chanceSource;
    //实体查询条件
    @TableField(value = "customerName", condition = SqlCondition.LIKE, whereStrategy = FieldStrategy.NOT_EMPTY)
    @NotNull(message = "客户名称不能为空")
    private String customerName;

    @TableField(value = "cgjl")
    private Integer cgjl;
    //实体查询条件
    @TableField(value = "overview", condition = SqlCondition.LIKE, whereStrategy = FieldStrategy.NOT_EMPTY)
    private String overview;

    @TableField(value = "linkMan")
    private String linkMan;

    @TableField(value = "linkPhone")
    private String linkPhone;

    @TableField(value = "description")
    private String description;
    //实体查询条件
    @TableField(value = "createMan", condition = SqlCondition.LIKE, whereStrategy = FieldStrategy.NOT_EMPTY)
    @NotNull(message = "创建人不能为空")
    private String createMan;

    @TableField(value = "createTime")
    private LocalDateTime createTime;

    @TableField(value = "assignMan")

    private Integer assignMan;

    @TableField(value = "assignTime")
    private LocalDateTime assignTime;

    @TableField(value = "state")
    private Integer state;

    @TableField(value = "devResult")
    private Integer devResult;
    //逻辑删除
    @TableLogic
    @TableField(value = "deleted")
    private Integer deleted;

    private static final long serialVersionUID = 1L;
}