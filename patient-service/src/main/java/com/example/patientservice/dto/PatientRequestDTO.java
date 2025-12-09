package com.example.patientservice.dto;

import com.example.patientservice.dto.validators.CreatePatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientRequestDTO {
    @NotBlank(message = "Name is required!")
    @Size(min = 1, max = 100, message = "Name Can't exceed 100 Char")
    private String name;

    @NotBlank(message = "Email is required!")
    @Email(message = "Email Should be valid!")
    private String email;

    @NotBlank(message = "Address is required!")
    private String address;

    @NotBlank(message = "DOB is required!")
    private String dateOfBirth;

    @NotBlank(groups = CreatePatientValidationGroup.class, message = "RegisterDate is required!")
    private String registeredDate;
}
