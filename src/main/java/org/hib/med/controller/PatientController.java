package org.hib.med.controller;

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
    public void createPatient(@RequestBody PatientDto patientDto) {
        patientService.create(patientDto);
    }

    @GetMapping
    public Set<PatientDto> getAllMedecin() {
        return patientService.getAll();
    }

    @GetMapping("/{codePat}")
    public PatientDto getPatient(@PathVariable String codePat) {
        return patientService.getOne(codePat);
    }

    @PutMapping("/update/{codePat}")
    public void updateMedecin(@PathVariable String codePat, @RequestBody PatientDto patientDto) {
        patientService.update(codePat, patientDto);
    }

    @DeleteMapping("/delete/{codePat}")
    public void deleteMedecin(@PathVariable String codePat) {
        patientService.delete(codePat);
    }


    @GetMapping("/search")
    public Set<PatientDto> searchPatient(@RequestParam String keyword) {
        return patientService.findByCodePatOrPatientLastNameOrPatientFirstName(keyword);
    }
}
