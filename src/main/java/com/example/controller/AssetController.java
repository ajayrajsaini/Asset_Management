package com.example.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.Asset;
import com.example.demo.AssignmentStatus;
import com.example.demo.Employee;
import com.example.repository.AssetRepository;
import com.example.repository.CategoryRepository;
import com.example.repository.EmployeeRepository;
import com.example.service.AssetService;

@RestController
@RequestMapping("/api/v1")
public class AssetController {

    @Autowired
    private AssetRepository assetRepository;

    @GetMapping("/assets")
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @GetMapping("/assets/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable(value = "id") Long assetId) {
        Optional<Asset> asset = assetRepository.findById(assetId);
        if (asset.isPresent()) {
            return ResponseEntity.ok().body(asset.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/assets")
    public Asset createAsset(@RequestBody Asset asset) {
        return assetRepository.save(asset);
    }

    @PutMapping("/assets/{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable(value = "id") Long assetId,
                                              @RequestBody Asset assetDetails) {
        Optional<Asset> asset = assetRepository.findById(assetId);
        if (asset.isPresent()) {
            Asset updatedAsset = asset.get();
            updatedAsset.setName(assetDetails.getName());
            updatedAsset.setPurchaseDate(assetDetails.getPurchaseDate());
            updatedAsset.setConditionNotes(assetDetails.getConditionNotes());
            updatedAsset.setCategory(assetDetails.getCategory());
            updatedAsset.setAssetStatus(assetDetails.getAssetStatus());
           // updatedAsset.setEmployee(assetDetails.getEmployee());
            return ResponseEntity.ok().body(assetRepository.save(updatedAsset));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/assets/{id}")
    public ResponseEntity<Asset> deleteAsset(@PathVariable(value = "id") Long assetId) {
        Optional<Asset> asset = assetRepository.findById(assetId);
        if (asset.isPresent()) {
            if (asset.get().getAssetStatus() == AssignmentStatus.ASSIGNED) {
                return ResponseEntity.badRequest().build();
            } else {
                assetRepository.delete(asset.get());
                return ResponseEntity.ok().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
