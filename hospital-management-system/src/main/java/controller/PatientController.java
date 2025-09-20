package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nt.dao.PatientDao;

import jakarta.servlet.http.HttpSession;
import model.Patient;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private final PatientDao patientDao;

    public PatientController(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    // Registration form
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient_register";
    }

    // Handle registration
    @PostMapping("/register")
    public String register(@ModelAttribute Patient patient, Model model) {
        int result = patientDao.save(patient);
        if (result > 0) {
            model.addAttribute("message", "Registration successful! Please login.");
            return "patient_login";
        } else {
            model.addAttribute("message", "Registration failed!");
            return "patient_register";
        }
    }

    // Login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "patient_login";
    }

    // Handle login
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        Patient p = patientDao.findByEmailAndPassword(email, password);
        if (p != null) {
            session.setAttribute("loggedInPatient", p);
            return "redirect:/appointment/book"; // redirect to appointment booking
        } else {
            model.addAttribute("message", "Invalid credentials!");
            return "patient_login";
        }
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/patient/login";
    }
}
