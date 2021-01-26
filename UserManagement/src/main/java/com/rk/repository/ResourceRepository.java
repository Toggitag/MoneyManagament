package com.rk.repository;

import com.rk.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Integer> {

    Boolean existsByResourcename(String resourcename);
}
