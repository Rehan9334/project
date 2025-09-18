package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Employee;

import java.util.List;

@Repository
public class EmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT * FROM employees",
                new BeanPropertyRowMapper<>(Employee.class));
    }

    public Employee findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM employees WHERE id = ?",
                new BeanPropertyRowMapper<>(Employee.class), id);
    }

    public int save(Employee employee) {
        return jdbcTemplate.update("INSERT INTO employees (name, role, salary, department) VALUES (?, ?, ?, ?)",
                employee.getName(), employee.getRole(), employee.getSalary(), employee.getDepartment());
    }

    public int update(Employee employee) {
        return jdbcTemplate.update("UPDATE employees SET name=?, role=?, salary=?, department=? WHERE id=?",
                employee.getName(), employee.getRole(), employee.getSalary(), employee.getDepartment(), employee.getId());
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM employees WHERE id=?", id);
    }
}
