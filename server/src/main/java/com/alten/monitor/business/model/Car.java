package com.alten.monitor.business.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "fleet", type = "cars")
public class Car {

	@Id
	private String registrationNumber;
	private String vehicleId;
	private String owner;
	private String type;
	private Integer capacity;
	private String address;
	private Boolean connected;
	
	public Car() {
		super();
	}
	public Car(String id, String vehicleId, String owner, String type,
			Integer capacity, String address, Boolean connected) {
		super();
		this.registrationNumber = id;
		this.vehicleId = vehicleId;
		this.owner = owner;
		this.type = type;
		this.capacity = capacity;
		this.address = address;
		this.connected = connected;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Boolean getConnected() {
		return connected;
	}
	public Boolean isConnected() {
		return connected;
	}
	public void setConnected(Boolean connected) {
		this.connected = connected;
	}
	@Override
	public String toString() {
		return "Car {registrationNumber='" + registrationNumber + '\'' + ", vehicleId='" + vehicleId + '\'' + ", owner='" + owner
				+ ", type='" + type + '\'' + ", capacity='" + capacity + '\'' + ", address='"
				+ address + '\'' + ", connected='" + connected + '\'' + "}";
	}
	
	
}
