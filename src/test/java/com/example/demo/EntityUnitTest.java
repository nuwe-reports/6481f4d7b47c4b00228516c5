package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.example.demo.entities.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestInstance(Lifecycle.PER_CLASS)
class EntityUnitTest {

	@Autowired
	private TestEntityManager entityManager;

	private Doctor d1;

	private Patient p1;

	private Room r1;

	private Appointment a1;
	private Appointment a2;
	private Appointment a3;

	private LocalDateTime startsAt;
	private LocalDateTime finishesAt;

	@Test
	public void testDoctorConstructor() throws Exception {
		String firstName = "firstName";
		String lastName = "lastName";
		int age = 20;
		String email = "email@example.com";

		d1 = new Doctor(firstName, lastName, age, email);

		assertEquals(firstName, d1.getFirstName());
		assertEquals(lastName, d1.getLastName());
		assertEquals(age, d1.getAge());
		assertEquals(email, d1.getEmail());
		assertEquals(0, d1.getId());
	}

	@Test
	public void testEmptyDoctorConstructor() throws Exception {

		d1 = new Doctor();

		assertNotNull(d1);
		assertNull(d1.getFirstName());
		assertNull(d1.getLastName());
		assertEquals(0, d1.getAge());
		assertNull(d1.getEmail());
		assertEquals(0, d1.getId());
	}

	@Test
	public void testGetIdDoctor() throws Exception {
		d1 = new Doctor();
		long expectedId = 123;

		d1.setId(expectedId);
		long actualId = d1.getId();

		assertEquals(expectedId, actualId);
	}

	@Test // QUITAR LOS SET Y CAMBIAR EL NOMBRE DE LOS GET
	public void testSetIdDoctor() throws Exception {

		d1 = new Doctor();
		long expectedId = 123;

		d1.setId(expectedId);
		long actualId = d1.getId();

		assertEquals(expectedId, actualId);
	}

	@Test
	public void testPatientConstructor() throws Exception {
		String firstName = "firstName";
		String lastName = "lastName";
		int age = 20;
		String email = "email@example.com";

		p1 = new Patient(firstName, lastName, age, email);

		assertEquals(firstName, p1.getFirstName());
		assertEquals(lastName, p1.getLastName());
		assertEquals(age, p1.getAge());
		assertEquals(email, p1.getEmail());
		assertEquals(0, p1.getId());
	}

	@Test
	public void testEmptyPatientConstructor() throws Exception {

		p1 = new Patient();

		assertNotNull(p1);
		assertNull(p1.getFirstName());
		assertNull(p1.getLastName());
		assertEquals(0, p1.getAge());
		assertNull(p1.getEmail());
		assertEquals(0, p1.getId());
	}

	@Test
	public void testGetIdPatient() throws Exception {
		p1 = new Patient();
		long expectedId = 123;

		p1.setId(expectedId);
		long actualId = p1.getId();

		assertEquals(expectedId, actualId);
	}

	@Test
	public void testSetIdPatient() throws Exception {

		p1 = new Patient();
		long expectedId = 123;

		p1.setId(expectedId);
		long actualId = p1.getId();

		assertEquals(expectedId, actualId);
	}

	@Test
	public void testRoomConstructor() throws Exception {

		String roomName = "roomName";

		r1 = new Room(roomName);

		assertEquals(roomName, r1.getRoomName());

	}

	@Test
	public void testEmptyRoomConstructor() throws Exception {

		r1 = new Room();

		assertNotNull(r1);
		assertNull(r1.getRoomName());

	}

	@Test
	public void testGetIRoomName() throws Exception {

		String roomName = "roomName";

		r1 = new Room(roomName);

		assertEquals(roomName, r1.getRoomName());
	}

	@Test
	public void testAppoinmentConstructor() throws Exception {

		String firstNameDoctor = "firstName";
		String lastNameDoctor = "lastName";
		int ageDoctor = 20;
		String emailDoctor = "email@example.com";

		d1 = new Doctor(firstNameDoctor, lastNameDoctor, ageDoctor, emailDoctor);

		String firstNamePatient = "firstName";
		String lastNamePatient = "lastName";
		int agePatient = 20;
		String emailPatient = "email@example.com";

		p1 = new Patient(firstNamePatient, lastNamePatient, agePatient, emailPatient);

		String roomName = "roomName";

		r1 = new Room(roomName);

		startsAt = LocalDateTime.now();
		finishesAt = LocalDateTime.now();

		a1 = new Appointment(p1, d1, r1, startsAt, finishesAt);

		assertEquals(p1, a1.getPatient());
		assertEquals(d1, a1.getDoctor());
		assertEquals(r1, a1.getRoom());
		assertEquals(startsAt, a1.getStartsAt());
		assertEquals(finishesAt, a1.getFinishesAt());
		assertEquals(0, a1.getId());
	}

	@Test
	public void testEmptyAppointmentConstructor() throws Exception {

		a1 = new Appointment();

		assertNotNull(a1);
		assertNull(a1.getPatient());
		assertNull(a1.getDoctor());
		assertNull(a1.getRoom());
		assertNull(a1.getStartsAt());
		assertNull(a1.getFinishesAt());
	}

	@Test
	public void testGetIdAppointment() throws Exception {
		a1 = new Appointment();
		long expectedId = 123;

		a1.setId(expectedId);
		long actualId = a1.getId();

		assertEquals(expectedId, actualId);
	}

	@Test
	public void testSetIdAppointment() throws Exception {

		a1 = new Appointment();
		long expectedId = 123;

		a1.setId(expectedId);
		long actualId = a1.getId();

		assertEquals(expectedId, actualId);
	}

	@Test
	public void testGetStartsAtdAppointment() throws Exception {
		a1 = new Appointment();
		LocalDateTime expectedStartsAt = LocalDateTime.now();

		a1.setStartsAt(expectedStartsAt);
		LocalDateTime actuaStartAt = a1.getStartsAt();

		assertEquals(expectedStartsAt, actuaStartAt);
	}

	@Test
	public void testSetStartsAtAppointment() throws Exception {

		a1 = new Appointment();
		LocalDateTime expectedStartsAt = LocalDateTime.now();

		a1.setStartsAt(expectedStartsAt);
		LocalDateTime actuaStartAt = a1.getStartsAt();

		assertEquals(expectedStartsAt, actuaStartAt);
	}

	@Test
	public void testGetFinishesAtdAppointment() throws Exception {
		a1 = new Appointment();
		LocalDateTime expectedFinishesAt = LocalDateTime.now();

		a1.setFinishesAt(expectedFinishesAt);
		LocalDateTime actuaFinishesAt = a1.getFinishesAt();

		assertEquals(expectedFinishesAt, actuaFinishesAt);
	}

	@Test
	public void testSetFinishesAtAppointment() throws Exception {

		a1 = new Appointment();
		LocalDateTime expectedFinishesAt = LocalDateTime.now();

		a1.setFinishesAt(expectedFinishesAt);
		LocalDateTime actuaFinishesAt = a1.getFinishesAt();

		assertEquals(expectedFinishesAt, actuaFinishesAt);
	}

	@Test
	public void testGetPatientdAppointment() throws Exception {
		a1 = new Appointment();

		String firstNamePatient = "firstName";
		String lastNamePatient = "lastName";
		int agePatient = 20;
		String emailPatient = "email@example.com";

		Patient expectedPatient = new Patient(firstNamePatient, lastNamePatient, agePatient, emailPatient);

		a1.setPatient(expectedPatient);
		Patient actuaPatient = a1.getPatient();

		assertEquals(expectedPatient, actuaPatient);
	}

	@Test
	public void testSetPatientAppointment() throws Exception {

		a1 = new Appointment();

		String firstNamePatient = "firstName";
		String lastNamePatient = "lastName";
		int agePatient = 20;
		String emailPatient = "email@example.com";

		Patient expectedPatient = new Patient(firstNamePatient, lastNamePatient, agePatient, emailPatient);

		a1.setPatient(expectedPatient);
		Patient actuaPatient = a1.getPatient();

		assertEquals(expectedPatient, actuaPatient);
	}

	@Test
	public void testGetDoctorAppointment() throws Exception {
		a1 = new Appointment();

		String firstNameDoctor = "firstName";
		String lastNameDoctor = "lastName";
		int ageDoctor = 20;
		String emailDoctor = "email@example.com";

		Doctor expectedDoctor = new Doctor(firstNameDoctor, lastNameDoctor, ageDoctor, emailDoctor);

		a1.setDoctor(expectedDoctor);
		Doctor actuaDoctor = a1.getDoctor();

		assertEquals(expectedDoctor, actuaDoctor);
	}

	@Test
	public void testSetDoctorAppointment() throws Exception {

		a1 = new Appointment();

		String firstNameDoctor = "firstName";
		String lastNameDoctor = "lastName";
		int ageDoctor = 20;
		String emailDoctor = "email@example.com";

		Doctor expectedDoctor = new Doctor(firstNameDoctor, lastNameDoctor, ageDoctor, emailDoctor);

		a1.setDoctor(expectedDoctor);
		Doctor actuaDoctor = a1.getDoctor();

		assertEquals(expectedDoctor, actuaDoctor);
	}

	@Test
	public void testGetRoomAppointment() throws Exception {

		a1 = new Appointment();

		String roomName = "roomName";

		Room expectedRoom = new Room(roomName);

		a1.setRoom(expectedRoom);
		Room actualRoom = a1.getRoom();

		assertEquals(expectedRoom, actualRoom);
	}

	@Test
	public void testSetRoomAppointment() throws Exception {

		a1 = new Appointment();

		String roomName = "roomName";

		Room expectedRoom = new Room(roomName);

		a1.setRoom(expectedRoom);
		Room actualRoom = a1.getRoom();

		assertEquals(expectedRoom, actualRoom);
	}

	@Test
	public void testOverLapsFalse() throws Exception {

		r1 = new Room("Room 1");
		a1 = new Appointment();
		a1.setRoom(r1);

		Room r2 = new Room("Room 2");
		a2 = new Appointment();
		a2.setRoom(r2);

		boolean overLaps = a1.overlaps(a2);

		assertFalse(overLaps);

	}

	@Test
	public void testOverLapsTrueGetStartAtEquals() throws Exception {

		r1 = new Room("Room 1");
		a1 = new Appointment();
		a1.setRoom(r1);
		a2 = new Appointment();
		a2.setRoom(r1);
		startsAt = LocalDateTime.now();
		a1.setStartsAt(startsAt);
		a2.setStartsAt(startsAt);

		boolean overLaps = a1.overlaps(a2);

		assertTrue(overLaps);

	}
	
	@Test
	public void testOverLapsTrueGetFinishesAtEquals() throws Exception {

		r1 = new Room("Room 1");
		a1 = new Appointment();
		a1.setRoom(r1);
		a2 = new Appointment();
		a2.setRoom(r1);
		startsAt = LocalDateTime.now();
		a1.setStartsAt(startsAt);
		a2.setStartsAt(startsAt);
		finishesAt = LocalDateTime.now();
		a1.setFinishesAt(finishesAt);
		a2.setFinishesAt(finishesAt);

		boolean overLaps = a1.overlaps(a2);

		assertTrue(overLaps);		

	}
	
	@Test
	public void testOverLapsTrueGetFinishAtIsAfter() throws Exception {

		r1 = new Room("Room 1");
		a1 = new Appointment();
		a2 = new Appointment();
		a1.setRoom(r1);
		a2.setRoom(r1);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
	    
	    LocalDateTime startsAt1= LocalDateTime.parse("18:30 24/04/2023", formatter);
	    LocalDateTime finishesAt1 = LocalDateTime.parse("21:30 24/04/2023", formatter);
	    LocalDateTime finishesAt2 = LocalDateTime.parse("19:30 24/04/2023", formatter);
	    
		a1.setStartsAt(startsAt1);
		a1.setFinishesAt(finishesAt1);
		a2.setFinishesAt(finishesAt2);

		boolean overLaps = a1.overlaps(a2);

		assertTrue(overLaps);
		

	}
	
	@Test
	public void testOverLapsTrueGetStartAtIsAfter() throws Exception {

		r1 = new Room("Room 1");
		a1 = new Appointment();
		a2 = new Appointment();
		a1.setRoom(r1);
		a2.setRoom(r1);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
	    
	    LocalDateTime startsAt1= LocalDateTime.parse("18:30 24/04/2023", formatter);
	    LocalDateTime startsAt2= LocalDateTime.parse("19:30 24/04/2023", formatter);
	    LocalDateTime finishesAt1 = LocalDateTime.parse("21:30 24/04/2023", formatter);
	    LocalDateTime finishesAt2 = LocalDateTime.parse("22:30 24/04/2023", formatter);

	    
		a1.setStartsAt(startsAt1);
		a2.setStartsAt(startsAt2);
		a1.setFinishesAt(finishesAt1);
		a2.setFinishesAt(finishesAt2);

		boolean overLaps = a1.overlaps(a2);

		assertTrue(overLaps);
		

	}
	
}
