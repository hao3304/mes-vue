package com.yizit.mes.service;

import com.yizit.mes.domain.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 李江峰
 * Date: 2017-12-14
 * Time: 11:36
 */
public interface MaterialService {
    Page<Material> listMaterial(Pageable pageable);
}
