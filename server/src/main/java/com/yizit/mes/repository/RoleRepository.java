package com.yizit.mes.repository;

import com.yizit.mes.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * Description: 角色资源库
 * User: 李江峰
 * Date: 2017-12-13
 * Time: 8:57
 */
public interface RoleRepository extends JpaRepository<Role,Long> {
    /**
     * 根据角色名称查找
     * @param name
     * @return
     */
    Role findByName(String name);
}
