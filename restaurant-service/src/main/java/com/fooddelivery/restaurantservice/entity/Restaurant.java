package com.fooddelivery.restaurantservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="restaurant")
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String address;
	
	private String cuisineType;
	
	private boolean open;
	
	private String approvalStatus;

	private String ownerName;
	
	public Restaurant() {
		super();
	}

	public Restaurant(Long id, String name, String address, String cuisineType, boolean open) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.cuisineType = cuisineType;
		this.open = open;
	}
	
	

	public Restaurant(Long id, String name, String address, String cuisineType, boolean open, String approvalStatus) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.cuisineType = cuisineType;
		this.open = open;
		this.approvalStatus = approvalStatus;
	}
	
	

	public Restaurant(Long id, String name, String address, String cuisineType, boolean open, String approvalStatus,
			String ownerName) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.cuisineType = cuisineType;
		this.open = open;
		this.approvalStatus = approvalStatus;
		this.ownerName = ownerName;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	
	

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", address=" + address + ", cuisineType=" + cuisineType
				+ ", open=" + open + "]";
	}
	
	
	
}
