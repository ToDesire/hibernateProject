package org.hib.med.entity;

import lombok.*;

import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
public class Medecin {
    private int codeDoc;
    private String doctorLastName;
    private String doctorFirstName;
    private String doctorGrade;
    private Set<Visite> visites;

    public void setCodeDoc(int codeDoc) {
        this.codeDoc = codeDoc;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }

    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }

    public void setDoctorGrade(String doctorGrade) {
        this.doctorGrade = doctorGrade;
    }

    public void setVisites(Set<Visite> visites) {
        this.visites = visites;
    }

    public int getCodeDoc() {
        return codeDoc;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    public String getDoctorGrade() {
        return doctorGrade;
    }

    public Set<Visite> getVisites() {
        return visites;
    }
}
