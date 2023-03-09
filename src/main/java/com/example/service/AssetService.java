package com.example.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.example.demo.Asset;
import com.example.demo.AssignmentStatus;
import com.example.demo.Employee;
import com.example.repository.AssetRepository;
@Service
public class AssetService {
    private final AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    public Optional<Asset> getAssetById(Long id) {
        return assetRepository.findById(id);
    }

    public Asset saveAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    public void deleteAsset(Asset asset) {
        if (asset.getAssetStatus() == AssignmentStatus.ASSIGNED) {
            throw new IllegalArgumentException("Cannot delete an assigned asset");
        }
        assetRepository.delete(asset);
    }

    public List<Asset> searchAssetsByName(String name) {
    	return assetRepository.findByNameContainingIgnoreCase(name);
    	}
    public List<Asset> getAvailableAssets() {
        return assetRepository.findByAssignmentStatus(AssignmentStatus.AVAILABLE);
    }

    public Asset assignAsset(Asset asset, Employee employee) {
        if (asset.getAssetStatus() != AssignmentStatus.AVAILABLE) {
            throw new IllegalArgumentException("Asset is not available for assignment");
        }
        asset.setAssetStatus(AssignmentStatus.ASSIGNED);
        asset.setEmployee(employee);
        return assetRepository.save(asset);
    }

    public Asset recoverAsset(Asset asset) {
        if (asset.getAssetStatus() != AssignmentStatus.ASSIGNED) {
            throw new IllegalArgumentException("Asset is not assigned");
        }
        asset.setAssetStatus(AssignmentStatus.RECOVERED);
        asset.setEmployee(null);
        return assetRepository.save(asset);
    }
}


