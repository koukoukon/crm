package me.hibiki.crm.domain;

import lombok.Data;

/**
 * @author 高弘昆
 * @date 2020/7/18 14:48
 */

@Data
public class UserRole {
    private User user;
    private Role role;
}
