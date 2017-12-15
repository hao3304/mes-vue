package com.yizit.mes.service.impl;

import com.yizit.mes.domain.Material;
import com.yizit.mes.repository.MaterialRepository;
import com.yizit.mes.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 李江峰
 * Date: 2017-12-14
 * Time: 11:38
 */
@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public Page<Material> listMaterial(Pageable pageable) {
        return materialRepository.findAll(pageable);
    }
}
