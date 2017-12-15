package com.yizit.mes.service;

import com.yizit.mes.domain.Authority;

import java.util.List;

/**
 * 权限控制模块
 */
public interface AuthorityService {

    /**
     * 查询所有权限
     * @return
     */
    List<Authority> listAuthority();

    Authority saveOrUpdateAuthority(Authority authority);

    void removeAuthority(Long id);

    boolean hasChildren(Long pid);

    Authority findById(Long id);
}
