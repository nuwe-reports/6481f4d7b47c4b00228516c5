
package com.example.demo;


import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import com.example.demo.controllers.*;
import com.example.demo.repositories.*;
import com.example.demo.entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TODO Implement all the unit test in its corresponding class. Make sure to be
 * as exhaustive as possible. Coverage is checked ;)
 */

@WebMvcTest(DoctorController.class)
class DoctorControllerUnitTest {

	@MockBean
	private DoctorRepository doctorRepository;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void shouldGetNoDoctors() throws Exception {
		List<Doctor> doctors = new ArrayList<Doctor>();
		when(doctorRepository.findAll()).thenReturn(doctors);
		mockMvc.perform(get("/api/doctors")).andExpect(status().isNoContent());

	}

	@Test
	void shouldGetAllDoctors() throws Exception {
		List<Doctor> doctors = new ArrayList<Doctor>();
		Doctor doctor = new Doctor("firstName", "lastName", 20, "email@example.com");
		doctors.add(doctor);

		when(doctorRepository.findAll()).thenReturn(doctors);
		mockMvc.perform(get("/api/doctors")).andExpect(status().isOk());

	}

	@Test
	void shouldGetADoctortById() throws Exception {
		Doctor doctor = new Doctor("firstName", "lastName", 20, "email@example.com");
		doctor.setId(1);

		Optional<Doctor> opt = Optional.of(doctor);

		assertThat(opt).isPresent();
		assertThat(opt.get().getId()).isEqualTo(doctor.getId());
		assertThat(doctor.getId()).isEqualTo(1);

		when(doctorRepository.findById(doctor.getId())).thenReturn(opt);
		mockMvc.perform(get("/api/doctors/" + doctor.getId())).andExpect(status().isOk());

	}

	@Test
	void shouldNotGetADoctorById() throws Exception {
		long id = 2;
		mockMvc.perform(get("/api/doctors/" + id)).andExpect(status().isNotFound());

	}

	@Test
	void shouldCreateDoctor() throws Exception {

		Doctor doctor = new Doctor("firstName", "lastName", 20, "email@example.com");
		mockMvc.perform(post("/api/doctor").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(doctor))).andExpect(status().isCreated());

	}

	@Test
	void shouldDeleteDoctorById() throws Exception {
		Doctor doctor = new Doctor("firstName", "lastName", 20, "email@example.com");
		doctor.setId(1);
		Optional<Doctor> opt = Optional.of(doctor);

		assertThat(opt).isPresent();
		assertThat(opt.get().getId()).isEqualTo(doctor.getId());
		assertThat(doctor.getId()).isEqualTo(1);

		when(doctorRepository.findById(doctor.getId())).thenReturn(opt);
		mockMvc.perform(delete("/api/doctors/" + doctor.getId())).andExpect(status().isOk());

	}

	@Test
	void shouldNotDeleteDoctorById() throws Exception {
		long id = 2;
		mockMvc.perform(delete("/api/doctors/" + id)).andExpect(status().isNotFound());

	}

	@Test
	void shouldDeleteAllDoctors() throws Exception {
		mockMvc.perform(delete("/api/doctors")).andExpect(status().isOk());

	}

}

@WebMvcTest(PatientController.class)
class PatientControllerUnitTest {

	@MockBean
	private PatientRepository patientRepository;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void shouldGetNoPatients() throws Exception {
		List<Patient> patients = new ArrayList<Patient>();
		when(patientRepository.findAll()).thenReturn(patients);
		mockMvc.perform(get("/api/patients")).andExpect(status().isNoContent());

	}

	@Test
	void shouldGetAllPatients() throws Exception {
		List<Patient> patients = new ArrayList<Patient>();
		Patient patient = new Patient("firstName", "lastName", 20, "email@example.com");
		patients.add(patient);

		when(patientRepository.findAll()).thenReturn(patients);
		mockMvc.perform(get("/api/patients")).andExpect(status().isOk());

	}

	@Test
	void shouldGetPatientById() throws Exception {
		Patient patient = new Patient("firstName", "lastName", 20, "email@example.com");
		patient.setId(1);

		Optional<Patient> opt = Optional.of(patient);

		assertThat(opt).isPresent();
		assertThat(opt.get().getId()).isEqualTo(patient.getId());
		assertThat(patient.getId()).isEqualTo(1);

		when(patientRepository.findById(patient.getId())).thenReturn(opt);
		mockMvc.perform(get("/api/patients/" + patient.getId())).andExpect(status().isOk());

	}

	@Test
	void shouldNotGetAPatienById() throws Exception {
		long id = 2;
		mockMvc.perform(get("/api/patients/" + id)).andExpect(status().isNotFound());

	}

	@Test
	void shouldCreatePatient() throws Exception {

		Patient patient = new Patient("firstName", "lastName", 20, "email@example.com");
		mockMvc.perform(post("/api/patient").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(patient))).andExpect(status().isCreated());

	}

	@Test
	void shouldDeletePatientById() throws Exception {
		Patient patient = new Patient("firstName", "lastName", 20, "email@example.com");
		patient.setId(1);
		Optional<Patient> opt = Optional.of(patient);

		assertThat(opt).isPresent();
		assertThat(opt.get().getId()).isEqualTo(patient.getId());
		assertThat(patient.getId()).isEqualTo(1);

		when(patientRepository.findById(patient.getId())).thenReturn(opt);
		mockMvc.perform(delete("/api/patients/" + patient.getId())).andExpect(status().isOk());

	}

	@Test
	void shouldNotDeletePatientById() throws Exception {
		long id = 2;
		mockMvc.perform(delete("/api/patients/" + id)).andExpect(status().isNotFound());

	}

	@Test
	void shouldDeleteAllPatients() throws Exception {
		mockMvc.perform(delete("/api/patients")).andExpect(status().isOk());

	}

}

@WebMvcTest(RoomController.class)
class RoomControllerUnitTest {

	@MockBean
	private RoomRepository roomRepository;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void shouldGetNoRooms() throws Exception {
		List<Room> rooms = new ArrayList<Room>();
		when(roomRepository.findAll()).thenReturn(rooms);
		mockMvc.perform(get("/api/rooms")).andExpect(status().isNoContent());

	}

	@Test
	void shouldGetAllRooms() throws Exception {
		List<Room> rooms = new ArrayList<Room>();
		Room room = new Room("Room");
		rooms.add(room);

		when(roomRepository.findAll()).thenReturn(rooms);
		mockMvc.perform(get("/api/rooms")).andExpect(status().isOk());

	}

	@Test
	void shouldGetRoomByName() throws Exception {
		Room room = new Room("Room");

		Optional<Room> opt = Optional.of(room);

		assertThat(opt).isPresent();
		assertThat(opt.get().getRoomName()).isEqualTo(room.getRoomName());
		assertThat(room.getRoomName()).isEqualTo("Room");

		when(roomRepository.findByRoomName(room.getRoomName())).thenReturn(opt);
		mockMvc.perform(get("/api/rooms/" + room.getRoomName())).andExpect(status().isOk());

	}

	@Test
	void shouldNotGetARoomByName() throws Exception {
		String name = "Room2";
		mockMvc.perform(get("/api/rooms/" + name)).andExpect(status().isNotFound());

	}

	@Test
	void shouldCreateRoom() throws Exception {

		Room room = new Room("Room");
		mockMvc.perform(post("/api/room").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(room))).andExpect(status().isCreated());

	}

	@Test
	void shouldDeleteRoomByName() throws Exception {
		Room room = new Room("Room");
		Optional<Room> opt = Optional.of(room);

		assertThat(opt).isPresent();
		assertThat(opt.get().getRoomName()).isEqualTo(room.getRoomName());
		assertThat(room.getRoomName()).isEqualTo("Room");

		when(roomRepository.findByRoomName(room.getRoomName())).thenReturn(opt);
		mockMvc.perform(delete("/api/rooms/" + room.getRoomName())).andExpect(status().isOk());

	}

	@Test
	void shouldNotDeleteRoomByName() throws Exception {
		String name = "Room2";
		mockMvc.perform(delete("/api/rooms/" + name)).andExpect(status().isNotFound());

	}

	@Test
	void shouldDeleteAllRoomss() throws Exception {
		mockMvc.perform(delete("/api/rooms")).andExpect(status().isOk());

	}

}
