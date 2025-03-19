package org.hib.med.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.hib.med.dto.PatientDto;
import org.hib.med.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/create")
    @Operation(
        summary = "Register new patient",
        description = "Add new patient to the database")
    @ApiResponse (responseCode = "200", description = "Patient registered successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public void createPatient(@RequestBody PatientDto patientDto) {
        patientService.create(patientDto);
    }

    @GetMapping
    @Operation(
        summary = "Get all patients",
        description = "Get all patients from the database")
    @ApiResponse(
        responseCode = "200",
        description = "Patients retrieved successfully",
        content = @Content( array = @ArraySchema(schema = @Schema(implementation = PatientDto.class)))
    )
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Patient not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public Set<PatientDto> getAllMedecin(@RequestParam String keyword) {
        return patientService.getAll(keyword);
    }

    @GetMapping("/{codePat}")
    @Operation(
        summary = "Get patient by code",
        description = "Get patient by code from the database")
    @ApiResponse(
        responseCode = "200",
        description = "Patient retrieved successfully",
        content = @Content(schema = @Schema(implementation = PatientDto.class))
    )
    @ApiResponse(responseCode = "404", description = "Patient not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public PatientDto getPatient(@PathVariable String codePat) {
        return patientService.getOne(codePat);
    }

    @PutMapping("/update/{codePat}")
    @Operation(
        summary = "Update patient",
        description = "Update patient in the database")
    @ApiResponse(responseCode = "200", description = "Patient updated successfully")
    @ApiResponse(responseCode = "404", description = "Patient not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    public void updateMedecin(@PathVariable String codePat, @RequestBody PatientDto patientDto) {
        patientService.update(codePat, patientDto);
    }

    @DeleteMapping("/delete/{codePat}")
    @Operation(
        summary = "Delete patient",
        description = "Delete patient from the database")
    @ApiResponse(responseCode = "200", description = "Patient deleted successfully")
    @ApiResponse(responseCode = "404", description = "Patient not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    public void deleteMedecin(@PathVariable String codePat) {
        patientService.delete(codePat);
    }

}
