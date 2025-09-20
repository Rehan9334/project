package com.nt.dao;

import java.time.LocalTime;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Appointment;

@Repository
public class AppointmentDao {
    private final JdbcTemplate jdbc;

    public AppointmentDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // Save appointment
    public int save(Appointment a) {
        String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time, reason, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        return jdbc.update(sql,
                a.getPatientId(),
                a.getDoctorId(),
                java.sql.Date.valueOf(a.getAppointmentDate()),
                a.getAppointmentTime().toString(),
                a.getReason(),
                a.getStatus());
    }

    // Get appointments for doctor
    @SuppressWarnings("deprecation")
	public List<Appointment> findByDoctorId(int doctorId) {
        String sql = "SELECT * FROM appointments WHERE doctor_id = ?";
        return jdbc.query(sql, new Object[]{doctorId}, (rs, rowNum) -> {
            Appointment ap = new Appointment();
            ap.setId(rs.getInt("id"));
            ap.setPatientId(rs.getInt("patient_id"));
            ap.setDoctorId(rs.getInt("doctor_id"));
            ap.setAppointmentDate(rs.getDate("appointment_date").toLocalDate());
            ap.setAppointmentTime(LocalTime.parse(rs.getString("appointment_time")));
            ap.setReason(rs.getString("reason"));
            ap.setStatus(rs.getString("status"));
            return ap;
        });
    }
        // Get appointments for patient
        @SuppressWarnings("deprecation")
		public List<Appointment> findByPatientId(int patientId) {
            String sql = "SELECT * FROM appointments WHERE patient_id = ?";
            return jdbc.query(sql, new Object[]{patientId}, (rs, rowNum) -> {
                Appointment ap = new Appointment();
                ap.setId(rs.getInt("id"));
                ap.setPatientId(rs.getInt("patient_id"));
                ap.setDoctorId(rs.getInt("doctor_id"));
                ap.setAppointmentDate(rs.getDate("appointment_date").toLocalDate());
                ap.setAppointmentTime(LocalTime.parse(rs.getString("appointment_time")));
                ap.setReason(rs.getString("reason"));
                ap.setStatus(rs.getString("status"));
                return ap;
            });
    }
}
