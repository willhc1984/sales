package com.sales.resources.exception;

import java.io.Serializable;
import java.time.LocalDate;

public class StandardError implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String msg;
	private LocalDate timeStamp;
	
	public StandardError(Integer status, String msg, LocalDate localDate) {
		super();
		this.status = status;
		this.msg = msg;
		this.timeStamp = localDate;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public LocalDate getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDate timeStamp) {
		this.timeStamp = timeStamp;
	}
	
}
