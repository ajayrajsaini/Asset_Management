package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Asset;
import com.example.demo.AssignmentStatus;
@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findByNameContainingIgnoreCase(String name);
    List<Asset> findByAssignmentStatus(AssignmentStatus assignmentStatus);
}

