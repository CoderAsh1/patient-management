package com.example.patientservice.service;

import com.example.patientservice.dto.PatientResponseDTO;
import com.example.patientservice.model.Patient;
import org.springframework.stereotype.Service;
import com.example.patientservice.repository.PatientRepository;
import com.example.patientservice.mapper.PatientMapper;


import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return  patients.stream().map(PatientMapper::toPatientResponseDTO).toList();
    }
}
