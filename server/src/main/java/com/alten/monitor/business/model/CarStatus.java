package com.alten.monitor.business.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "status", type = "carstatus")
public class CarStatus {
	
	@Id
	private String id;
	private String registrationNumber;
	private String connected;
	private Date timestamp;
	
	
	
	public CarStatus() {
		super();
		this.timestamp = new Date(System.currentTimeMillis());
	}


	public CarStatus(String registrationNumber, String connected) {
		super();
		this.registrationNumber = registrationNumber;
		this.connected = connected;
		this.timestamp = new Date(System.currentTimeMillis());
	}


	public CarStatus(String id, String registrationNumber, String connected, Date timestamp) {
		super();
		this.id = id;
		this.registrationNumber = registrationNumber;
		this.connected = connected;
		this.timestamp = timestamp;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getRegistrationNumber() {
		return registrationNumber;
	}


	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}


	public String getConnected() {
		return connected;
	}


	public void setConnected(String connected) {
		this.connected = connected;
	}


	public Date getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	
	@Override
	public String toString() {
		return "CarStatus {id='" + id + '\'' + ", registrationNumber='" + registrationNumber + '\'' + ", connected='" + connected + '\'' + ", timestamp='" + timestamp.toString() + '\'' + "}";
	}
	
}
