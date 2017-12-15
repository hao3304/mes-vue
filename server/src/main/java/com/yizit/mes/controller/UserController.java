package com.yizit.mes.controller;

import com.yizit.mes.domain.User;
import com.yizit.mes.service.impl.UserServiceImpl;
import com.yizit.mes.util.ConstraintViolationExceptionHandler;
import com.yizit.mes.vo.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;

@RestController
@RequestMapping("${spring.data.rest.base-path}/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/list")
    @ApiOperation(value = "用户列表查询",notes = "根据username查询")
    @PreAuthorize("hasAuthority('system:user')")
    public ResponseEntity<Response> list(
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int pageSize) {

        Pageable pageable = new PageRequest(pageIndex -1, pageSize);
        Page<User> page = userService.listUser(pageable);

        return ResponseEntity.ok().body(new Response("0","",page.getTotalElements(), page.getContent()));
    }

    @PostMapping
    @ApiOperation(value = "新增用户",notes = "新增用户")
    @PreAuthorize("hasAuthority('system:user:add')")
    public ResponseEntity<Response> add(User user) {

        User t = userService.findByUserName(user.getUsername());
        if(t != null) {
            return ResponseEntity.ok().body(new Response("400","已经相同用户名！"));
        }
        user.setId(null);
        try {
            userService.saveOrUpdateUser(user);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.ok().body(new Response("400","处理失败！", ConstraintViolationExceptionHandler.getMessage(e)));
        }
        return ResponseEntity.ok().body(new Response("0","操作成功！", user));
    }

    @PutMapping
    @ApiOperation(value = "编辑用户",notes = "编辑用户")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public ResponseEntity<Response> update(User user) {
        User t = userService.findByUserName(user.getUsername());
        if(t != null && user.getId() == null) {
            return ResponseEntity.ok().body(new Response("400","已经相同用户名！"));
        }
        if( user.getId() == null) {
            return ResponseEntity.ok().body(new Response("400","请指定对象！"));
        }
        try {
            userService.saveOrUpdateUser(user);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.ok().body(new Response("400","处理失败！", ConstraintViolationExceptionHandler.getMessage(e)));
        }
        return ResponseEntity.ok().body(new Response("0","操作成功！", user));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户",notes = "根据id删除用户")
    @PreAuthorize("hasAuthority('system:user:del')")
    public ResponseEntity<Response>  remove(@PathVariable("id") Long id) {
        try {
            userService.removeUser(id);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response("400","处理失败！", e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response("0","删除成功！"));
    }

}
