package com.yizit.mes.controller;

import com.yizit.mes.domain.Authority;
import com.yizit.mes.service.impl.AuthorityServiceImpl;
import com.yizit.mes.util.ConstraintViolationExceptionHandler;
import com.yizit.mes.vo.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/authority")
public class AuthorityController {

    @Autowired
    private AuthorityServiceImpl authorityService;

    @GetMapping
    private ModelAndView index(Model model) {
        return new ModelAndView("authority/index");
    }

    @GetMapping("/list.json")
    private ResponseEntity<Response> list() {
        List<Authority> lst = authorityService.listAuthority();
        Integer count = lst.size();
        return ResponseEntity.ok().body(new Response("0","ok",count.longValue(),lst));
    }

    @PostMapping
    @ApiOperation(value = "新增权限",notes = "新增权限")
    public ResponseEntity<Response> save(Authority authority) {
        try {
            authorityService.saveOrUpdateAuthority(authority);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.ok().body(new Response("400","处理失败！", ConstraintViolationExceptionHandler.getMessage(e)));
        }
        return ResponseEntity.ok().body(new Response("0","操作成功！", authority));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Response> remove(@PathVariable("id") Long id) {

        if(authorityService.hasChildren(id)){
            return ResponseEntity.ok().body(new Response("400","请先删除子集！"));
        }

        try {
            authorityService.removeAuthority(id);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response("400","处理失败！", e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response("0","删除成功！"));
    }

}
