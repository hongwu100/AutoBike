package com.motor.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public interface MotorDAO_interface {
	public void insert(MotorVO motorVO);

	public void update(MotorVO motorVO);

	public void delete(String motno);

	public MotorVO findByPrimaryKey(String motno);

	public List<MotorVO> getAll();

	public Set<MotorVO> getMotorsByModelType(String modtype);

	public Set<MotorVO> getMotorsByLocNo(String locno);

	public Set<MotorVO> getMotorsByManuDate(Timestamp start_time, Timestamp end_time);
}
