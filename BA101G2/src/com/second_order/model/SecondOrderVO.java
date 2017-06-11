package com.second_order.model;

import java.io.Serializable;
import java.sql.Date;

public class SecondOrderVO implements Serializable {

	private Integer second_no;
	private Integer mem_no;
	private Integer motor_no;
	private Date second_order_date;
	private String second_status;

	public SecondOrderVO(){}

	public SecondOrderVO(Integer second_no, Integer mem_no, Integer motor_no, Date second_order_date, String second_status){
		this.second_no = second_no;
		this.mem_no = mem_no;
		this.motor_no = motor_no;
		this.second_order_date = second_order_date;
		this.second_status = second_status;
	}

	public Integer getSecondNo() {
		return second_no;
	}

	public void setSecondNo(Integer second_no) {
		this.second_no = second_no;
	}

	public Integer getMemNo() {
		return mem_no;
	}

	public void setMemNo(Integer mem_no) {
		this.mem_no = mem_no;
	}

	public Integer getMotorNo() {
		return motor_no;
	}

	public void setMotorNo(Integer motor_no) {
		this.motor_no = motor_no;
	}

	public Date getSecondOrderDate() {
		return second_order_date;
	}

	public void setSecondOrderDate(Date second_order_date) {
		this.second_order_date = second_order_date;
	}

	public String getSecondStatus() {
		return second_status;
	}

	public void setSecondStatus(String second_status) {
		this.second_status = second_status;
	}

}