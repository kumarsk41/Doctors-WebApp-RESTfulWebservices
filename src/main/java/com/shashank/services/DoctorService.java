package com.shashank.services;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.shashank.models.*;
import com.shashank.repo.*;

@Service
@Transactional
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorNotAvailRepository notAvailRepository;

    @Autowired
    private DoctorOnlineRepository onlineRepository;

    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    public List<Doctor> getDoctorsBySpeciality(String speciality) {
        return doctorRepository.findBySpeciality(speciality);
    }

    public List<Doctor> getDoctors(String state, String city, String speciality) {
        return doctorRepository.findByStateAndCityAndSpeciality(state, city, speciality);
    }

    public boolean register(Doctor doctor) {
        if (doctorRepository.existsById(doctor.getEmail()))
            return false;

        doctor.setDoctorDetails(new DoctorDetails());
        doctor.setDoctorAvail(new DoctorAvail());
        doctorRepository.save(doctor);
        return true;
    }

    public Doctor getDoctor(String email) {

        Doctor doctor = doctorRepository.findById(email).orElse(null);
        if (doctor == null) return null;

        boolean updated = false;

        // create details if missing
        if (doctor.getDoctorDetails() == null) {
            doctor.setDoctorDetails(new DoctorDetails());
            updated = true;
        }

        // create availability if missing
        if (doctor.getDoctorAvail() == null) {
            doctor.setDoctorAvail(new DoctorAvail());
            updated = true;
        }

        if (updated) {
            doctorRepository.save(doctor);
        }

        return doctor;
    }

    public Doctor updateDocAvail(String email, DoctorAvail newAvail) {
        Doctor doctor = doctorRepository.findById(email).orElse(null);
        if (doctor == null) return null;

        DoctorAvail da = doctor.getDoctorAvail();
        da.setMax_eve_apmt(newAvail.getMax_eve_apmt());
        da.setMax_mor_apmt(newAvail.getMax_mor_apmt());
        da.setMon_eve(newAvail.getMon_eve());
        da.setMon_mor(newAvail.getMon_mor());
        da.setTue_eve(newAvail.getTue_eve());
        da.setTue_mor(newAvail.getTue_mor());
        da.setWed_eve(newAvail.getWed_eve());
        da.setWed_mor(newAvail.getWed_mor());
        da.setThu_eve(newAvail.getThu_eve());
        da.setThu_mor(newAvail.getThu_mor());
        da.setFri_eve(newAvail.getFri_eve());
        da.setFri_mor(newAvail.getFri_mor());
        da.setSat_eve(newAvail.getSat_eve());
        da.setSat_mor(newAvail.getSat_mor());
        da.setSun_eve(newAvail.getSun_eve());
        da.setSun_mor(newAvail.getSun_mor());

        doctorRepository.save(doctor);
        return doctor;
    }

    public Doctor updateDoctor(Doctor doctor) {
        Doctor d = doctorRepository.findById(doctor.getEmail()).orElse(null);
        if (d == null) return null;

        d.setArea(doctor.getArea());
        d.setCity(doctor.getCity());
        d.setName(doctor.getName());
        d.setState(doctor.getState());
        d.setSpeciality(doctor.getSpeciality());

        DoctorDetails dd = d.getDoctorDetails();
        dd.setDob(doctor.getDoctorDetails().getDob());
        dd.setExperience(doctor.getDoctorDetails().getExperience());
        dd.setGender(doctor.getDoctorDetails().getGender());
        dd.setPhone(doctor.getDoctorDetails().getPhone());
        dd.setQualification(doctor.getDoctorDetails().getQualification());

        doctorRepository.save(d);
        return d;
    }

    public boolean updateDoctorPhoto(String email, byte[] photo) {

        Doctor doctor = doctorRepository.findById(email).orElse(null);
        if (doctor == null) return false;

        if (doctor.getDoctorDetails() == null)
            doctor.setDoctorDetails(new DoctorDetails());

        doctor.getDoctorDetails().setPhoto(photo);
        doctorRepository.save(doctor);
        return true;
    }

    public boolean addDocNotAvail(DoctorNotAvail dna) {
    	if (!notAvailRepository.findByEmailAndDocDate(dna.getEmail(), dna.getDocDate()).isEmpty())
    	    return false;

        notAvailRepository.save(dna);
        return true;
    }

    public List<DoctorNotAvail> getDocNotAvail(String email) {
        return notAvailRepository.findByEmail(email);
    }

    public boolean cancelDocNotAvail(int id) {
        if (!notAvailRepository.existsById(id)) return false; // it gives exception if it is not available
        notAvailRepository.deleteById(id);
        return true;
    }

    public boolean updatePassword(String email, String newPassword) {
        Doctor doctor = doctorRepository.findById(email).orElse(null);
        if (doctor == null) return false;
        doctor.setPassword(newPassword);
        doctorRepository.save(doctor);
        return true;
    }

    public byte[] getDoctorPhoto(String email) {

        Doctor doctor = doctorRepository.findById(email).orElse(null);
        if (doctor == null) return null;

        // create details automatically if missing (old DB records)
        if (doctor.getDoctorDetails() == null) {
            doctor.setDoctorDetails(new DoctorDetails());
            doctorRepository.save(doctor);
            return null;
        }

        return doctor.getDoctorDetails().getPhoto();
    }

    public DoctorOnline getDoctorOnline(String email) {
        return onlineRepository.findById(email).orElse(null);
    }

    public DoctorOnline doctorOnline(String email, String speciality) {
        DoctorOnline online = onlineRepository.findById(email).orElse(null);

        if (online == null) {
            String roomID = (int) Math.round(Math.random() * 10000) + "";
            online = new DoctorOnline();
            online.setDocEmail(email);
            online.setSpeciality(speciality);
            online.setRoomId(roomID);
            onlineRepository.save(online);
        }
        return online;
    }

    public void doctorOffline(String email) {
        onlineRepository.deleteById(email);
    }
}


























