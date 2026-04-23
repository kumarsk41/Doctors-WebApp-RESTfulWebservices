package com.shashank.models;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * 
 */
@Entity
//----lombok
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Appointments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonProperty("doctor_email")
	@Column(name = "doctor_email")
	private String doctorEmail;

	@JsonProperty("user_email")
	@Column(name = "user_email")
	private String userEmail;

	private String name;

	private String status;

	@JsonProperty("doc_booking_date")
	@Column(name = "doc_booking_date")
	private Date docBookingDate;

	@JsonProperty("doc_booking_time")
	@Column(name = "doc_booking_time")
	private String docBookingTime;
	
	@JsonProperty("booking_date_time")
	@Column(name = "booking_date_time")
	private java.util.Date bookingDateTime;
	
	
	
}
	
	
	
	
	
//	===================================================================================================
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
//	private String doctor_email;
//	private String user_email;
//	private String name;
//	private String status;
//	
//	private Date doc_booking_date; //Appointment Day
//	private String doc_booking_time;
//	
//	private java.util.Date booking_date_time; //day of booking
//	
//}
