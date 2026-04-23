package com.shashank.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.shashank.models.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, String> {

    List<Doctor> findBySpeciality(String speciality);

    List<Doctor> findByStateAndCityAndSpeciality(String state, String city, String speciality);
}