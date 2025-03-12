package org.hib.med.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class Visite {
    private int codeVisite;
    private int codeMed;
    private String codePat;
    private Date dateVisite;
    private Medecin medecin;

    public void setCodeMed(int codeMed) {
        this.codeMed = codeMed;
    }

    public void setCodePat(String codePat) {
        this.codePat = codePat;
    }

    public void setDateVisite(Date dateVisite) {
        this.dateVisite = dateVisite;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setCodeVisite(int codeVisite) {
        this.codeVisite = codeVisite;
    }

    public int getCodeVisite() {
        return codeVisite;
    }

    public int getCodeMed() {
        return codeMed;
    }

    public String getCodePat() {
        return codePat;
    }

    public Date getDateVisite() {
        return dateVisite;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public Patient getPatient() {
        return patient;
    }

    private Patient patient;
}
