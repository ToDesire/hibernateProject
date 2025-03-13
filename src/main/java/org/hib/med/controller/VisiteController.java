package org.hib.med.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.hib.med.dto.VisitDetailsDto;
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
    @Operation(
        summary = "Register new visit",
        description = "Add new visit to the database")
    @ApiResponse (responseCode = "200", description = "Visit registered successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public void createVisite(@RequestBody VisiteDto visiteDto) {
        visitService.create(visiteDto);
    }

    @GetMapping
    @Operation(
        summary = "Get all visits",
        description = "Get all visits from the database")
    @ApiResponse(
        responseCode = "200",
        description = "Visits retrieved successfully",
        content = @Content( array =  @ArraySchema (schema = @Schema(implementation = VisitDetailsDto.class)))
    )
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public Set<VisitDetailsDto> getVisites() {
        return visitService.getAll();
    }

    @GetMapping("/{codeVisite}")
    @Operation(
        summary = "Get visit by code",
        description = "Get visit by code from the database")
    @ApiResponse(
        responseCode = "200",
        description = "Visit retrieved successfully",
        content = @Content(schema = @Schema(implementation = VisitDetailsDto.class))
    )
    @ApiResponse(responseCode = "404", description = "Visit not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public VisitDetailsDto getVisite(@PathVariable int codeVisite) {
        return visitService.getOne(codeVisite);
    }

    @PutMapping("/update/{codeVisite}")
    @Operation(
        summary = "Update visit",
        description = "Update visit in the database")
    @ApiResponse(responseCode = "200", description = "Visit updated successfully")
    @ApiResponse(responseCode = "404", description = "Visit not found")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public void updateVisite(@PathVariable int codeVisite, @RequestBody VisiteDto visiteDto) {
        visitService.update(codeVisite, visiteDto);
    }

    @DeleteMapping("/delete/{codeVisite}")
    @Operation(
        summary = "Delete visit",
        description = "Delete visit from the database")
    @ApiResponse(responseCode = "200", description = "Visit deleted successfully")
    @ApiResponse(responseCode = "404", description = "Visit not found")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public void deleteVisite(@PathVariable int codeVisite) {
        visitService.delete(codeVisite);
    }
}
