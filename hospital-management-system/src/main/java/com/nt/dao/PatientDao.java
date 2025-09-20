package com.nt.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Patient;

@Repository
public class PatientDao {
    private final JdbcTemplate jdbc;

    public PatientDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // Register new patient
    public int save(Patient p) {
        String sql = "INSERT INTO patients (name, email, password, phone) VALUES (?, ?, ?, ?)";
        return jdbc.update(sql, p.getName(), p.getEmail(), p.getPassword(), p.getPhone());
    }

    // Login check
    @SuppressWarnings("deprecation")
	public Patient findByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM patients WHERE email = ? AND password = ?";
        return jdbc.query(sql, new Object[]{email, password}, rs -> {
            if (rs.next()) {
                Patient p = new Patient();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setEmail(rs.getString("email"));
                p.setPassword(rs.getString("password"));
                p.setPhone(rs.getString("phone"));
                return p;
            }
            return null;
        });
    }
}
