package com.member.model;
import java.io.Serializable;
import java.sql.Date;

public class MemberVO implements Serializable {

	private Integer mem_no;
	private String mem_name;
	private String mem_sex;
	private String mem_mail;
	private Integer mem_phone;
	private String mem_addr;
	private String mem_account;
	private String mem_pwd;
	private byte[] mem_id1;
	private byte[] mem_id2;
	private byte[] mem_license1;
	private Date mem_birthday;
	
	private String mem_status;

	public MemberVO(){}

	public MemberVO(Integer mem_no, String mem_name, String mem_sex, String mem_mail, Integer mem_phone, String mem_addr, String mem_account, String mem_pwd, byte[] mem_id1, byte[] mem_id2, byte[] mem_license1, Date mem_birthday, String mem_status){
		this.mem_no = mem_no;
		this.mem_name = mem_name;
		this.mem_sex = mem_sex;
		this.mem_mail = mem_mail;
		this.mem_phone = mem_phone;
		this.mem_addr = mem_addr;
		this.mem_account = mem_account;
		this.mem_pwd = mem_pwd;
		this.mem_id1 = mem_id1;
		this.mem_id2 = mem_id2;
		this.mem_license1 = mem_license1;
		this.mem_birthday = mem_birthday;
		this.mem_status = mem_status;
	}

	public Integer getMemNo() {
		return mem_no;
	}

	public void setMemNo(Integer mem_no) {
		this.mem_no = mem_no;
	}

	public String getMemName() {
		return mem_name;
	}

	public void setMemName(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMemSex() {
		return mem_sex;
	}

	public void setMemSex(String mem_sex) {
		this.mem_sex = mem_sex;
	}

	public String getMemMail() {
		return mem_mail;
	}

	public void setMemMail(String mem_mail) {
		this.mem_mail = mem_mail;
	}

	public Integer getMemPhone() {
		return mem_phone;
	}

	public void setMemPhone(Integer mem_phone) {
		this.mem_phone = mem_phone;
	}

	public String getMemAddr() {
		return mem_addr;
	}

	public void setMemAddr(String mem_addr) {
		this.mem_addr = mem_addr;
	}

	public String getMemAccount() {
		return mem_account;
	}

	public void setMemAccount(String mem_account) {
		this.mem_account = mem_account;
	}

	public String getMemPwd() {
		return mem_pwd;
	}

	public void setMemPwd(String mem_pwd) {
		this.mem_pwd = mem_pwd;
	}

	public byte[] getMemId1() {
		return mem_id1;
	}

	public void setMemId1(byte[] mem_id1) {
		this.mem_id1 = mem_id1;
	}

	public byte[] getMemId2() {
		return mem_id2;
	}

	public void setMemId2(byte[] mem_id2) {
		this.mem_id2 = mem_id2;
	}

	public byte[] getMemLicense1() {
		return mem_license1;
	}

	public void setMemLicense1(byte[] mem_license1) {
		this.mem_license1 = mem_license1;
	}

	public Date getMemBirthday() {
		return mem_birthday;
	}

	public void setMemBirthday(Date mem_birthday) {
		this.mem_birthday = mem_birthday;
	}

	public String getMemStatus() {
		return mem_status;
	}

	public void setMemStatus(String mem_status) {
		this.mem_status = mem_status;
	}

}