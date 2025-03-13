package org.hib.med.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hib.med.enums.PatientGender;


import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    private String codePat;
    private String patientLastName;
    private String patientFirstName;

    @Enumerated(EnumType.STRING)
    private PatientGender gender;

    private String address;
    private Set<Visite> visites;

    public void setCodePat(String codePat) {
        this.codePat = codePat;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public void setGender(PatientGender gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setVisites(Set<Visite> visites) {
        this.visites = visites;
    }

    public String getCodePat() {
        return codePat;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public PatientGender getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public Set<Visite> getVisites() {
        return visites;
    }
}
