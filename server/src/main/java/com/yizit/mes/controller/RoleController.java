package com.yizit.mes.controller;

import com.yizit.mes.domain.Authority;
import com.yizit.mes.domain.Role;
import com.yizit.mes.service.impl.AuthorityServiceImpl;
import com.yizit.mes.service.impl.RoleServiceImpl;
import com.yizit.mes.util.ConstraintViolationExceptionHandler;
import com.yizit.mes.vo.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 角色控制器
 * User: 李江峰
 * Date: 2017-12-13
 * Time: 8:51
 */

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private AuthorityServiceImpl authorityService;

    @GetMapping
    @PreAuthorize("hasAuthority('system:role')")
    public ModelAndView index() {
        return new ModelAndView("role/index");
    }

    @GetMapping("/list.json")
    @PreAuthorize("hasAuthority('system:role')")
    public ResponseEntity<Response> list(
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int pageSize) {

        Pageable pageable = new PageRequest(pageIndex -1, pageSize);
        Page<Role> page = roleService.listRole(pageable);

        return ResponseEntity.ok().body(new Response("0","",page.getTotalElements(), page.getContent()));
    }

    @PostMapping
    @ApiOperation(value = "新增角色",notes = "新增角色")
    @PreAuthorize("hasAuthority('system:role:add')")
    public ResponseEntity<Response> save(Role role) {

        Role t = roleService.findByName(role.getName());
        if(t != null && role.getId() == null) {
            return ResponseEntity.ok().body(new Response("400","已经相同名称角色！"));
        }

        role.setId(null);

        try {
            roleService.saveOrUpdateRole(role);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.ok().body(new Response("400","处理失败！", ConstraintViolationExceptionHandler.getMessage(e)));
        }
        return ResponseEntity.ok().body(new Response("0","操作成功！", role));
    }


    @PutMapping
    @ApiOperation(value = "编辑角色",notes = "编辑角色")
    @PreAuthorize("hasAuthority('system:role:edit')")
    public ResponseEntity<Response> update(Role role) {

        Role t = roleService.findByName(role.getName());
        if(t != null && role.getId() == null) {
            return ResponseEntity.ok().body(new Response("400","已经相同名称角色！"));
        }
        if(role.getId() == null) {
            return ResponseEntity.ok().body(new Response("400","未找到对象！"));
        }

        try {
            roleService.saveOrUpdateRole(role);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.ok().body(new Response("400","处理失败！", ConstraintViolationExceptionHandler.getMessage(e)));
        }
        return ResponseEntity.ok().body(new Response("0","操作成功！", role));
    }





    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除角色",notes = "根据id删除角色")
    @PreAuthorize("hasAuthority('system:role:del')")
    public ResponseEntity<Response>  remove(@PathVariable("id") Long id) {

        try {
            roleService.removeRole(id);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response("400","处理失败！", e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response("0","删除成功！"));
    }

    @PostMapping("/{id}")
    @ApiOperation(value = "权限管理",notes = "权限管理")
    @PreAuthorize("hasAuthority('system:role:auth')")
    public ResponseEntity<Response>  roleAndAuth(@PathVariable("id") Long id,@RequestParam("authority[]") Long[] auths) {
        List<Authority> authorities = new ArrayList<>();
        for(Long aid : auths) {
            authorities.add(authorityService.findById(aid));
        }

        Role role = roleService.findById(id);
        role.setAuthorityList(authorities);

        try {
            roleService.saveOrUpdateRole(role);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.ok().body(new Response("400","处理失败！", ConstraintViolationExceptionHandler.getMessage(e)));
        }

        return ResponseEntity.ok().body(new Response("0","操作成功！", role));
    }
}
