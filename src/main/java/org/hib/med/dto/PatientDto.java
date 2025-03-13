package org.hib.med.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {
    private String codePat;
    private String patientLastName;
    private String patientFirstName;
    private String gender;
    private String address;

    public void setCodePat(String codePat) {
        this.codePat = codePat;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }
}
