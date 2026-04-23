package com.shashank.services;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.shashank.mail.MailSend;
import com.shashank.models.Appointments;
import com.shashank.models.Doctor;
import com.shashank.repo.AppointmentRepo;

@Service
@Transactional
public class AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;
    
    @Autowired
    private MailSend mailSend;

    public List<Appointments> getByUserEmail(String userEmail) {
        return appointmentRepo.findByUserEmail(userEmail);
    }

    public List<Appointments> getByDoctorEmail(String doctorEmail) {
        return appointmentRepo.findByDoctorEmail(doctorEmail);
    }

    public boolean delete(int id) {
        if (!appointmentRepo.existsById(id)) return false;
        Appointments appointment = appointmentRepo.findById(id).orElse(null);
        appointmentRepo.deleteById(id);
        return true;
    }

    public boolean updateStatus(int id, String status) {
        Appointments appointment = appointmentRepo.findById(id).orElse(null);
        if (appointment == null) return false;

        appointment.setStatus(status);
        appointmentRepo.save(appointment);
        
        String subject = "Appointment is  behan ke lund gand tod dunga teri" + appointment.getStatus()  ;
        String body = "Hello,\n\n"
                + "Your appointment has been "+ appointment.getStatus()+"\n\n"
                + "Doctor: " + appointment.getDoctorEmail() + "\n"
                + "Date: " + appointment.getDocBookingDate() + "\n"
                + "Time: " + appointment.getDocBookingTime() + "\n\n"
                + "Thank you.";
        System.out.println("Sending mail to: " + appointment.getUserEmail());
        mailSend.doMailSend(appointment.getUserEmail(), subject, body);
        body += "Patient name: "+appointment.getName();
        mailSend.doMailSend(appointment.getDoctorEmail(), subject, body);

        System.out.println("mail send ");
        return true;
    }

    public String addAppointment(Appointments appointment) {

        Date currentDate = new Date();
        appointment.setBookingDateTime(currentDate);

        if (appointment.getDocBookingDate() == null)
            return "Please select appointment date!";

        if (appointment.getDocBookingDate().before(currentDate))
            return "Wrong Date Selected!";

        List<Appointments> existing =
                appointmentRepo.findByUserEmailAndDocBookingDateAndDocBookingTimeAndStatus(
                        appointment.getUserEmail(),
                        appointment.getDocBookingDate(),
                        appointment.getDocBookingTime(),
                        "pending");

        if (!existing.isEmpty())
            return "You Already Booked!";

        appointmentRepo.save(appointment);
System.out.println("kfkgfg");
     // send email
     String subject = "Appointment Booking Confirmation";

     String body = "Hello,\n\n"
             + "Your appointment has been successfully booked.\n\n"
             + "Doctor: " + appointment.getDoctorEmail() + "\n"
             + "Date: " + appointment.getDocBookingDate() + "\n"
             + "Time: " + appointment.getDocBookingTime() + "\n\n"
             + "Status: Pending\n\n"
             + "Thank you.";
     System.out.println("Sending mail to: " + appointment.getUserEmail());
     mailSend.doMailSend(appointment.getUserEmail(), subject, body);
     body += "\n Patient name: "+appointment.getName();

     mailSend.doMailSend(appointment.getDoctorEmail(), subject, body);


     return "Booking Successfull!";
    }
}









