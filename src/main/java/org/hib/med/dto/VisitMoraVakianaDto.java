package org.hib.med.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class VisitMoraVakianaDto {
    private Date dateVisite;
    private MedecinDto medecin;
    private PatientDto patient;

    public void setDateVisite(Date dateVisite) {
        this.dateVisite = dateVisite;
    }

    public void setMedecin(MedecinDto medecin) {
        this.medecin = medecin;
    }

    public void setPatient(PatientDto patient) {
        this.patient = patient;
    }

    public Date getDateVisite() {
        return dateVisite;
    }

    public MedecinDto getMedecin() {
        return medecin;
    }

    public PatientDto getPatient() {
        return patient;
    }
}
