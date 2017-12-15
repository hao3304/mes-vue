package com.yizit.mes.service.impl;

import com.yizit.mes.domain.Authority;
import com.yizit.mes.repository.AuthorityRepository;
import com.yizit.mes.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {


    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public List<Authority> listAuthority() {
        return authorityRepository.findAll();
    }

    @Override
    public Authority saveOrUpdateAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Override
    public void removeAuthority(Long id) {
        authorityRepository.delete(id);
    }

    @Override
    public boolean hasChildren(Long pid) {
         return authorityRepository.existsByPid(pid);
    }

    @Override
    public Authority findById(Long id) {
        return authorityRepository.findOne(id);
    }
}
