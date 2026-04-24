# Doctor Appointment Web App (Backend - REST API)

## Project Overview

This is the backend service for a Doctor Appointment System built using Spring Boot.

It provides RESTful APIs for managing doctors and appointments.  
The frontend (Thymeleaf client) communicates with this backend using RestTemplate.

---

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA (Hibernate)
- MySQL
- REST APIs
- Maven

---

## API Endpoints

### Doctor APIs
- GET /doctors → Get all doctors
- POST /doctors → Add new doctor

### Appointment APIs
- POST /appointments → Book appointment
- GET /appointments → Get all appointments

---
## Screenshots
<img width="1440" height="900" alt="SearchDoctor Api" src="https://github.com/user-attachments/assets/f4cd16bb-19ca-4642-b008-2b64593f807c" />
<img width="1440" height="900" alt="GetUser Api" src="https://github.com/user-attachments/assets/aee184ad-4e8b-4309-95ec-4cbf2627ba41" />
<img width="1440" height="900" alt="GetAppointments Api" src="https://github.com/user-attachments/assets/b5d013e2-ad0d-4188-8ab8-3a5ac0db8226" />




## Sample API Response

```json
{
  "doctor_id": 1,
  "doctor_name": "Dr. Sharma",
  "specialization": "Cardiology"
}
