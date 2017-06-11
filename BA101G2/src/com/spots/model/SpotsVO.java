package com.spots.model;

import java.io.Serializable;

public class SpotsVO implements Serializable {

	private Integer spots_no;
	private Integer loc_no;
	private Float spots_long;
	private Float spots_lat;
	private String spots_name;

	public SpotsVO(){}

	public SpotsVO(Integer spots_no, Integer loc_no, Float spots_long, Float spots_lat, String spots_name){
		this.spots_no = spots_no;
		this.loc_no = loc_no;
		this.spots_long = spots_long;
		this.spots_lat = spots_lat;
		this.spots_name = spots_name;
	}

	public Integer getSpotsNo() {
		return spots_no;
	}

	public void setSpotsNo(Integer spots_no) {
		this.spots_no = spots_no;
	}

	public Integer getLocNo() {
		return loc_no;
	}

	public void setLocNo(Integer loc_no) {
		this.loc_no = loc_no;
	}

	public Float getSpotsLong() {
		return spots_long;
	}

	public void setSpotsLong(Float spots_long) {
		this.spots_long = spots_long;
	}

	public Float getSpotsLat() {
		return spots_lat;
	}

	public void setSpotsLat(Float spots_lat) {
		this.spots_lat = spots_lat;
	}

	public String getSpotsName() {
		return spots_name;
	}

	public void setSpotsName(String spots_name) {
		this.spots_name = spots_name;
	}

}