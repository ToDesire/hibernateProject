package org.hib.med.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
        summary = "Add new medecin",
        description = "Add new medecin to the database")
    @ApiResponse(responseCode = "200", description = "Medecin added successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public void createMedecin(@RequestBody MedecinDto medecinDto) {
        medecinService.create(medecinDto);
    }

    @GetMapping
    @Operation(
        summary = "Get all medecins",
        description = "Get all medecins from the database")
    @ApiResponse(
        responseCode = "200",
        description = "Medecins retrieved successfully",
        content = @Content(array = @ArraySchema(schema = @Schema(implementation = MedecinDto.class)))
    )
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public Set<MedecinDto> getAllMedecin() {
        return medecinService.getAll();
    }

    @GetMapping("/{codeMed}")
    @Operation(
        summary = "Get medecin by code",
        description = "Get medecin by code from the database")
    @ApiResponse(
        responseCode = "200",
        description = "Medecin retrieved successfully",
        content = @Content(schema = @Schema(implementation = MedecinDto.class))
    )
    @ApiResponse(responseCode = "404", description = "Medecin not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public MedecinDto getMedecin(@PathVariable int codeMed) {
        return medecinService.getOne(codeMed);
    }

    @PutMapping("/update/{codeMed}")
    @Operation(
        summary = "Update medecin",
        description = "Update medecin in the database")
    @ApiResponse(responseCode = "200", description = "Medecin updated successfully")
    @ApiResponse(responseCode = "404", description = "Medecin not found")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public void updateMedecin(@PathVariable int codeMed, @RequestBody MedecinDto medecinDto) {
        medecinService.update(codeMed, medecinDto);
    }

    @DeleteMapping("/delete/{codeMed}")
    @Operation(
        summary = "Delete medecin",
        description = "Delete medecin from the database")
    @ApiResponse(responseCode = "200", description = "Medecin deleted successfully")
    @ApiResponse(responseCode = "404", description = "Medecin not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public void deleteMedecin(@PathVariable int codeMed) {
        medecinService.delete(codeMed);
    }
}
