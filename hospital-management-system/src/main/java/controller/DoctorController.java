package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nt.dao.DoctorDao;

import model.Doctor;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorDao doctorDao;

    public DoctorController(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    // Show registration form
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "doctor_register";
    }

    // Handle registration
    @PostMapping("/register")
    public String registerDoctor(@ModelAttribute Doctor doctor, Model model) {
        int result = doctorDao.save(doctor);
        if (result > 0) {
            model.addAttribute("message", "Doctor registered successfully!");
        } else {
            model.addAttribute("message", "Registration failed, try again.");
        }
        return "doctor_register";
    }

    // Show login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "doctor_login";
    }

    // Handle login
    @PostMapping("/login")
    public String loginDoctor(@RequestParam String email, @RequestParam String password, Model model) {
        Doctor doctor = doctorDao.findByEmail(email);
        if (doctor != null && doctor.getPassword().equals(password)) {
            model.addAttribute("doctor", doctor);
            return "doctor_dashboard"; // dashboard page
        } else {
            model.addAttribute("error", "Invalid credentials!");
            return "doctor_login";
        }
    }
}
