package com.login.controller;

import com.login.entity.Plan;



import com.login.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Plans")
@CrossOrigin(origins = "http://localhost:3000") // Adjust this based on your frontend port!
public class PlanController {

    @Autowired
    private PlanService planService;

    // GET all plans
    @GetMapping
    public List<Plan> getAllPlans() {
        return planService.getAllPlans();
    }

    // GET plan by ID
    @GetMapping("/{id}")
    public ResponseEntity<Plan> getPlanById(@PathVariable Long id) {
        return planService.getPlanById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE a new plan
    @PostMapping
    public ResponseEntity<Plan> addPlan(@RequestBody Plan plan) {
        Plan createdPlan = planService.addPlan(plan);
        return ResponseEntity.ok(createdPlan);
    }

    // UPDATE existing plan
    @PutMapping("/{id}")
    public ResponseEntity<Plan> updatePlan(@PathVariable Long id, @RequestBody Plan plan) {
        Plan updatedPlan = planService.updatePlan(id, plan);
        return ResponseEntity.ok(updatedPlan);
    }

    // DELETE plan
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
        planService.deletePlan(id);
        return ResponseEntity.noContent().build();
    }
    
 // GET plans by category id
    @GetMapping("/category/{categoryId}")
    public List<Plan> getPlansByCategory(@PathVariable Long categoryId) {
        return planService.getPlansByCategory(categoryId);
    }

}
