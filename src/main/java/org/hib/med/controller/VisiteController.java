package org.hib.med.controller;

import org.hib.med.dto.VisitMoraVakianaDto;
import org.hib.med.dto.VisiteDto;
import org.hib.med.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/visite")
public class VisiteController {
    @Autowired
    private VisitService visitService;

    @PostMapping("/create")
    public void createVisite(@RequestBody VisiteDto visiteDto) {
        visitService.create(visiteDto);
    }

    @GetMapping
    public Set<VisitMoraVakianaDto> getVisites() {
        return visitService.getAll();
    }

    @GetMapping("/{codeVisite}")
    public VisitMoraVakianaDto getVisite(@PathVariable int codeVisite) {
        return visitService.getOne(codeVisite);
    }

    @PutMapping("/update/{codeVisite}")
    public void updateVisite(@PathVariable int codeVisite, @RequestBody VisiteDto visiteDto) {
        visitService.update(codeVisite, visiteDto);
    }

    @DeleteMapping("/delete/{codeVisite}")
    public void deleteVisite(@PathVariable int codeVisite) {
        visitService.delete(codeVisite);
    }
}
