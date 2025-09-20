package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment 
{
  private Integer id;
  private Integer patientId;
  private Integer doctorId;
  private LocalDate appointmentDate;
  private LocalTime appointmentTime;
  private String reason;
  private String status;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getPatientId() {
	return patientId;
}
public void setPatientId(Integer patientId) {
	this.patientId = patientId;
}
public Integer getDoctorId() {
	return doctorId;
}
public void setDoctorId(Integer doctorId) {
	this.doctorId = doctorId;
}
public LocalDate getAppointmentDate() {
	return appointmentDate;
}
public void setAppointmentDate(LocalDate appointmentDate) {
	this.appointmentDate = appointmentDate;
}
public LocalTime getAppointmentTime() {
	return appointmentTime;
}
public void setAppointmentTime(LocalTime appointmentTime) {
	this.appointmentTime = appointmentTime;
}
public String getReason() {
	return reason;
}
public void setReason(String reason) {
	this.reason = reason;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
  
  
  
  
}
