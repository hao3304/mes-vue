package com.yizit.mes.controller;

import com.yizit.mes.domain.Authority;
import com.yizit.mes.domain.User;
import com.yizit.mes.security.SecurityUser;
import com.yizit.mes.security.SecurityUtil;
import com.yizit.mes.service.impl.AuthorityServiceImpl;
import com.yizit.mes.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 获取菜单
 * User: 李江峰
 * Date: 2017-12-13
 * Time: 14:07
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AuthorityServiceImpl authorityService;

    @GetMapping("{id}")
    @ResponseBody
    public List<Authority> one(@PathVariable("id")Long id){
        SecurityUser securityUser = SecurityUtil.getUser();
        List<Authority> authorities;

        if(SecurityUtil.isRoot()) {
            authorities = authorityService.listAuthority();
        }else{
            User user = userService.findByUserName(securityUser.getUsername());
            authorities = user.getRole().getAuthorityList();
        }

        List<Authority> menu = new ArrayList<>();
        for(Authority authority : authorities) {
            if(authority.getType() == Authority.Type.Menu && authority.getPid() == id) {
                menu.add(authority);
            }
        }
        return menu;
    }
}
