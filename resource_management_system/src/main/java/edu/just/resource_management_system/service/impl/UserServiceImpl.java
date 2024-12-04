package edu.just.resource_management_system.service.impl;

import edu.just.resource_management_system.mapper.UserMapper;
import edu.just.resource_management_system.pojo.User;
import edu.just.resource_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public User findUserById(Long id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public User login(String userName, String password) {
        return userMapper.login(userName,password);
    }

    @Override
    public void saveUser(User user) {
        userMapper.register(user);
    }

    @Override
    public void modifyUserInfo(String userName, String email, String phoneNumber, Long id) {
        userMapper.UpdateUserInfo(userName,email,phoneNumber,id);
    }

    @Override
    public void modifyUserPassword(String userPassword, Long id) {
        userMapper.UpdateUserPassword(userPassword,id);
    }
}
