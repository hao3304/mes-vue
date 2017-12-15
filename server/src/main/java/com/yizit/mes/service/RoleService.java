package com.yizit.mes.service;

import com.yizit.mes.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 角色模块
 */
public interface RoleService {

    /**
     * 查询所有角色
     * @return
     */
    Page<Role> listRole(Pageable pageable);

    List<Role> findAll();

    Role saveOrUpdateRole(Role role);

    void removeRole(Long id);

    Role findByName(String name);

    Role findById(Long id);
}
