package com.shashank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shashank.models.DoctorOnline;

public interface DoctorOnlineRepository extends JpaRepository<DoctorOnline, String> {
}