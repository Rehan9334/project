package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.EmployeeDao;
import model.Employee;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public List<Employee> findAll() { return employeeDao.findAll(); }
    public Employee findById(int id) { return employeeDao.findById(id); }
    public int save(Employee employee) { return employeeDao.save(employee); }
    public int update(Employee employee) { return employeeDao.update(employee); }
    public int delete(int id) { return employeeDao.delete(id); }
}
