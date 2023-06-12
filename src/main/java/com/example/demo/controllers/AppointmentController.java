package com.example.demo.controllers;

import com.example.demo.repositories.*;
import com.example.demo.entities.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AppointmentController {

	@Autowired
	AppointmentRepository appointmentRepository;

	@GetMapping("/appointments")
	public ResponseEntity<List<Appointment>> getAllAppointments() {
		List<Appointment> appointments = new ArrayList<>();

		appointmentRepository.findAll().forEach(appointments::add);

		if (appointments.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(appointments, HttpStatus.OK);
	}

	@GetMapping("/appointments/{id}")
	public ResponseEntity<Appointment> getAppointmentById(@PathVariable("id") long id) {
		Optional<Appointment> appointment = appointmentRepository.findById(id);

		if (appointment.isPresent()) {
			return new ResponseEntity<>(appointment.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/appointment")
	/**
	 * 
	 * @param appointment contiene la informacion de la cita a crear
	 * @return OK y una lista con todas las citas si si crea la cita NOT_ACEPTABLE
	 *         si hay ya una cita en la misma sala y en el mismo intervalo de tiempo
	 *         BAD_REQUEST si no hay un intervalo de 20 mintuos entre el inicio y el
	 *         fin de la cita
	 */
	public ResponseEntity<List<Appointment>> createAppointment(@RequestBody Appointment appointment) {

		List<Appointment> appointments = filterAppointmentSameTimeSamePlace(appointment);

		if (appointments.size() > 0) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		int diff = differenceBetween20MinAppointments(appointment);

		if (diff < 0) {
			Appointment ap = new Appointment(appointment.getPatient(), appointment.getDoctor(), appointment.getRoom(),
					appointment.getStartsAt(), appointment.getFinishesAt());
			appointmentRepository.save(ap);
			return new ResponseEntity<>(appointmentRepository.findAll(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/appointments/{id}")
	public ResponseEntity<HttpStatus> deleteAppointment(@PathVariable("id") long id) {

		Optional<Appointment> appointment = appointmentRepository.findById(id);

		if (!appointment.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		appointmentRepository.deleteById(id);

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@DeleteMapping("/appointments")
	public ResponseEntity<HttpStatus> deleteAllAppointments() {
		appointmentRepository.deleteAll();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Filtra citas en la misma sala y en el mismo intervalo de tiempo
	 * 
	 * @param appointment contiene la informacion de la cita por la que se va a
	 *                    filtrar
	 * @return una lista con todas las citas con los mismos datos del filtro
	 */
	public List<Appointment> filterAppointmentSameTimeSamePlace(Appointment appointment) {
		List<Appointment> appointments = new ArrayList<>();
		return appointmentRepository.findAll().stream()
				.filter(a -> a.getRoom().getRoomName().equals(appointment.getRoom().getRoomName())
						&& a.getStartsAt().equals(appointment.getStartsAt())
						&& a.getFinishesAt().equals(appointment.getFinishesAt()))
				.collect(Collectors.toList());
	}

	/**
	 * Compara las fechas de inicio y fin de las citas, sumando a la primera 20
	 * minutos para comprobar que al menos cada cita pueda durar ese tiempo
	 * 
	 * @param appointment contiene la informacion de la cita 
	 * @return int > 0 si la diferencia de tiempo es menor que 20 minutos
	 * 		   int < 0 si la diferencia de tiempo es mayor que 20 minutos
	 * 		   int = 0 si no hay diferencia 
	 * 
	 */
	public int differenceBetween20MinAppointments(Appointment appointment) {
		LocalDateTime startsAtInc = appointment.getStartsAt().plusMinutes(20L);
		int diff = startsAtInc.compareTo(appointment.getFinishesAt());
		return diff;
	}

}
