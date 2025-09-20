package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nt.dao.AppointmentDao;

import jakarta.servlet.http.HttpSession;
import model.Appointment;
import model.Patient;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentDao appointmentDao;

    public AppointmentController(AppointmentDao appointmentDao) {
        this.appointmentDao = appointmentDao;
    }

    // Show booking form (patient must be logged in)
    @GetMapping("/book")
    public String showBookingForm(HttpSession session, Model model) {
        Patient loggedInPatient = (Patient) session.getAttribute("loggedInPatient");
        if (loggedInPatient == null) {
            return "redirect:/patient/login"; // redirect to login if not logged in
        }

        Appointment appointment = new Appointment();
        appointment.setPatientId(loggedInPatient.getId()); // auto-set patientId

        model.addAttribute("appointment", appointment);
        model.addAttribute("patientName", loggedInPatient.getName());
        return "appointment_book";
    }

    // Handle booking
    @PostMapping("/book")
    public String bookAppointment(@ModelAttribute Appointment appointment,
                                  HttpSession session,
                                  Model model) {
        Patient loggedInPatient = (Patient) session.getAttribute("loggedInPatient");
        if (loggedInPatient == null) {
            return "redirect:/patient/login";
        }

        appointment.setPatientId(loggedInPatient.getId()); // enforce session patientId
        appointment.setStatus("SCHEDULED");

        int result = appointmentDao.save(appointment);
        if (result > 0) {
            model.addAttribute("message", "Appointment booked successfully!");
        } else {
            model.addAttribute("message", "Booking failed, please try again.");
        }

        model.addAttribute("appointment", new Appointment());
        model.addAttribute("patientName", loggedInPatient.getName());
        return "appointment_book";
    }

    // View appointments by doctor
    @GetMapping("/doctor/{doctorId}")
    public String viewAppointments(@PathVariable int doctorId, Model model) {
        List<Appointment> appointments = appointmentDao.findByDoctorId(doctorId);
        model.addAttribute("appointments", appointments);
        return "doctor_appointments";
    }   
        // View appointments by logged-in patient
        @GetMapping("/my")
        public String viewMyAppointments(HttpSession session, Model model) {
            Patient loggedInPatient = (Patient) session.getAttribute("loggedInPatient");
            if (loggedInPatient == null) {
                return "redirect:/patient/login";
            }

            List<Appointment> appointments = appointmentDao.findByPatientId(loggedInPatient.getId());
            model.addAttribute("appointments", appointments);
            model.addAttribute("patientName", loggedInPatient.getName());
            return "patient_appointments";
    }
}