package com.example.patientservice.service;

import com.example.patientservice.dto.PatientRequestDTO;
import com.example.patientservice.dto.PatientResponseDTO;
import com.example.patientservice.exception.EmailAlreadyExistException;
import com.example.patientservice.exception.PatientNotFoundException;
import com.example.patientservice.model.Patient;
import org.springframework.stereotype.Service;
import com.example.patientservice.repository.PatientRepository;
import com.example.patientservice.mapper.PatientMapper;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return  patients.stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patient) {
        if(patientRepository.existsByEmail(patient.getEmail())) {
            throw new EmailAlreadyExistException("Email already exist");
        }
        Patient newPatient = patientRepository.save(PatientMapper.toModel(patient));
        return PatientMapper.toDTO(newPatient);
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientDto) {
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new PatientNotFoundException("Patient not found"));
        patient.setName(patientDto.getName());
        patient.setEmail(patientDto.getEmail());
        patient.setAddress(patientDto.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientDto.getDateOfBirth()));
        patientRepository.save(patient);
        return PatientMapper.toDTO(patient);
    }
}
