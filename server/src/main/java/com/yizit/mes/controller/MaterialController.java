package com.yizit.mes.controller;

import com.yizit.mes.domain.Material;
import com.yizit.mes.service.impl.MaterialServiceImpl;
import com.yizit.mes.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * Description: 物料控制器
 * User: 李江峰
 * Date: 2017-12-14
 * Time: 11:39
 */

@RequestMapping("/material")
@RestController
public class MaterialController {

    @Autowired
    private MaterialServiceImpl materialService;

    @GetMapping
    public ModelAndView index(Model model) {
        model.addAttribute("title","物料管理");

        return new ModelAndView("material/index","materialModel",model);
    }

    @GetMapping("list.json")
    public ResponseEntity<Response> list(
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int pageSize) {

        Pageable pageable = new PageRequest(pageIndex -1, pageSize);
        Page<Material> page = materialService.listMaterial(pageable);

        return ResponseEntity.ok().body(new Response("0","",page.getTotalElements(), page.getContent()));
    }

}
