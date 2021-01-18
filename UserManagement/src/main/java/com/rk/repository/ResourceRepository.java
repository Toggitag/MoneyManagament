package com.rk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rk.entity.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

	Optional<Resource> findByresourcename(String resourcename);
}
