package com.iitbh_elogix.summer.weighbridge_automation.dto;

public class RFID {

	private Integer id;
	private String uuid;
	private boolean status;

	public RFID() {
	}

	public RFID(Integer id, String uuid, boolean status) {
		this.id = id;
		this.uuid = uuid;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUUID() {
		return uuid;
	}

	public void setUUID(String uuid) {
		this.uuid = uuid;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
