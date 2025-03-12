package org.hib.med.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class VisiteDto {
    private Date dateVisite;
    private int codeMed;
    private String codePat;

    public void setDateVisite(Date dateVisite) {
        this.dateVisite = dateVisite;
    }

    public void setCodeMed(int codeMed) {
        this.codeMed = codeMed;
    }

    public void setCodePat(String codePat) {
        this.codePat = codePat;
    }

    public Date getDateVisite() {
        return dateVisite;
    }

    public int getCodeMed() {
        return codeMed;
    }

    public String getCodePat() {
        return codePat;
    }
}
