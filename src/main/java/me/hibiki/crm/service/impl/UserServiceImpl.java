package me.hibiki.crm.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.hibiki.crm.mapper.UserMapper;
import me.hibiki.crm.domain.User;
import me.hibiki.crm.service.UserService;
/**
 * @author 高弘昆
 * @date 2020/7/15 16:16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

}
