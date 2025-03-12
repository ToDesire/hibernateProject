package org.hib.med.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    private String codePat;
    private String patientLastName;
    private String patientFirstName;
    private String sex;
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

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getSex() {
        return sex;
    }

    public String getAddress() {
        return address;
    }

    public Set<Visite> getVisites() {
        return visites;
    }
}
