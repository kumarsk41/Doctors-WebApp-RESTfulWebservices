package com.shashank.repo;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.shashank.models.DoctorNotAvail;

public interface DoctorNotAvailRepository extends JpaRepository<DoctorNotAvail, Integer> {

    List<DoctorNotAvail> findByEmail(String email);

    List<DoctorNotAvail> findByEmailAndDocDate(String email, Date docDate);
}