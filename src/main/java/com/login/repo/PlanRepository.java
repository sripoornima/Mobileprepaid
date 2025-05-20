package com.login.repo;

import com.login.entity.Plan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
	List<Plan> findByCategoryId(Long categoryId);

}
