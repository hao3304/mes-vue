package com.yizit.mes.service.impl;

import com.yizit.mes.domain.Role;
import com.yizit.mes.repository.RoleRepository;
import com.yizit.mes.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 实现角色服务方法
 * User: 李江峰
 * Date: 2017-12-13
 * Time: 8:56
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Page<Role> listRole(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role saveOrUpdateRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void removeRole(Long id) {
        roleRepository.delete(id);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findOne(id);
    }
}
