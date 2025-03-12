package org.hib.med.controller;

import org.hib.med.dto.MedecinDto;
import org.hib.med.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Set;

@RestController
@RequestMapping("/api/medecin")
public class MedecinController {
    @Autowired
    private MedecinService medecinService;

    @PostMapping("/create")
    public void createMedecin(@RequestBody MedecinDto medecinDto) {
        medecinService.create(medecinDto);
    }

    @GetMapping
    public Set<MedecinDto> getAllMedecin() {
        return medecinService.getAll();
    }

    @GetMapping("/{codeMed}")
    public MedecinDto getMedecin(@PathVariable int codeMed) {
        return medecinService.getOne(codeMed);
    }

    @PutMapping("/update/{codeMed}")
    public void updateMedecin(@PathVariable int codeMed, @RequestBody MedecinDto medecinDto) {
        medecinService.update(codeMed, medecinDto);
    }

    @DeleteMapping("/delete/{codeMed}")
    public void deleteMedecin(@PathVariable int codeMed) {
        medecinService.delete(codeMed);
    }
}
