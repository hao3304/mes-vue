package com.yizit.mes.service;


import com.yizit.mes.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService  {

    User saveOrUpdateUser(User user);

    void removeUser(Long id);

    User getUserById(Long id);

    User findByUserName(String name);

    Page<User> listUser(Pageable pageable);

    String login(String username,String password);
}
