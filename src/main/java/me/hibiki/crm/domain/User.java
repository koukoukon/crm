package me.hibiki.crm.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author 高弘昆
 * @date 2020/7/15 16:16
 */
@Data
@TableName(value = "`user`")
@Validated
public class User implements Serializable {
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @TableField(value = "user_name")
    @NotEmpty
    private String userName;

    @TableField(value = "user_password")
    @NotEmpty
    private String userPassword;

    @TableField(value = "user_real_name")
    private String userRealName;

    @TableField(value = "user_email")
    private String userEmail;

    @TableField(value = "user_phone")
    private String userPhone;

    @TableField(value = "role_id")
    private Integer roleId;

    private static final long serialVersionUID = 1L;
}