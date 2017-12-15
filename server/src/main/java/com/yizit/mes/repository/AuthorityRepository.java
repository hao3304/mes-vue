package com.yizit.mes.repository;

import com.yizit.mes.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {

     boolean existsByPid(Long pid);
}
