package org.hib.med.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class MedecinDto {
    private String doctorLastName;
    private String doctorFirstName;
    private String doctorGrade;

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }

    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }

    public void setDoctorGrade(String doctorGrade) {
        this.doctorGrade = doctorGrade;
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
}
