package com.shashank.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shashank.mail.MailSend;

import jakarta.persistence.EntityManager;

@Repository
public class AdminRepo {
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	MailSend ms;
	
	
}
