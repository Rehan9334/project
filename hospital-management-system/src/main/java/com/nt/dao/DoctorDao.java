package com.nt.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Doctor;

@Repository
public class DoctorDao {
    private final JdbcTemplate jdbc;

    public DoctorDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // Save doctor
    public int save(Doctor d) {
        String sql = "INSERT INTO doctors (name, email, specialty, password) VALUES (?, ?, ?, ?)";
        return jdbc.update(sql, d.getName(), d.getEmail(), d.getSpecialty(), d.getPassword());
    }

    // Find doctor by email
    @SuppressWarnings("deprecation")
	public Doctor findByEmail(String email) {
        String sql = "SELECT * FROM doctors WHERE email = ?";
        try {
            return jdbc.queryForObject(sql, new Object[]{email}, (rs, rowNum) -> {
                Doctor doc = new Doctor();
                doc.setId(rs.getInt("id"));
                doc.setName(rs.getString("name"));
                doc.setEmail(rs.getString("email"));
                doc.setSpecialty(rs.getString("specialty"));
                doc.setPassword(rs.getString("password"));
                return doc;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // Find doctor by ID
    @SuppressWarnings("deprecation")
	public Doctor findById(int id) {
        String sql = "SELECT * FROM doctors WHERE id = ?";
        try {
            return jdbc.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
                Doctor doc = new Doctor();
                doc.setId(rs.getInt("id"));
                doc.setName(rs.getString("name"));
                doc.setEmail(rs.getString("email"));
                doc.setSpecialty(rs.getString("specialty"));
                doc.setPassword(rs.getString("password"));
                return doc;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}