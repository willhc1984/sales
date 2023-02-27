package com.sales.resources.exception;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StandardError implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String msg;
	private LocalDate timeStamp;
	private List<Error> errors = new ArrayList<>();
	
	public StandardError(Integer status, String msg, LocalDate localDate) {
		super();
		this.status = status;
		this.msg = msg;
		this.timeStamp = localDate;
	}
	
	public StandardError(Integer status, String msg, LocalDate localDate, List<Error> errors) {
		super();
		this.status = status;
		this.msg = msg;
		this.timeStamp = localDate;
		this.errors = errors;
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
	public List<Error> getErrors() {
		return errors;
	}
	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
	
}
