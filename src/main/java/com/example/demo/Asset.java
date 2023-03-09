package com.example.demo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate purchaseDate;

    @Column(nullable = false)
    private String conditionNotes;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private AssignmentStatus assetStatus;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee assignedTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getConditionNotes() {
        return conditionNotes;
    }

    public void setConditionNotes(String conditionNotes) {
        this.conditionNotes = conditionNotes;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public AssignmentStatus getAssetStatus() {
        return assetStatus;
    }

    public void setAssetStatus(AssignmentStatus assetStatus) {
        this.assetStatus = assetStatus;
    }

    public Employee getAssignedTo() {
        return assignedTo;
    }

    public void setEmployee(Employee assignedTo) {
        this.assignedTo = assignedTo;
    }

	public void setAssignedTo(Employee assignedTo) {
		this.assignedTo = assignedTo;
	}

	
}

