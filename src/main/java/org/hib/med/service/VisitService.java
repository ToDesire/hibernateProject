package org.hib.med.service;


import org.hib.med.dto.MedecinDto;
import org.hib.med.dto.PatientDto;
import org.hib.med.dto.VisitDetailsDto;
import org.hib.med.dto.VisiteDto;
import org.hib.med.entity.Medecin;
import org.hib.med.entity.Patient;
import org.hib.med.entity.Visite;
import org.hib.med.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VisitService {

    @Autowired
    private ModelMapper modelMapper;

    public void create(VisiteDto visiteDto){

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction;
            Medecin medecin = session.get(Medecin.class, visiteDto.getCodeMed());
            Patient patient = session.get(Patient.class, visiteDto.getCodePat());
            if (medecin != null && patient != null) {
                Visite visite = new Visite();
                visite.setDateVisite(visiteDto.getDateVisite());
                visite.setCodeMed(visiteDto.getCodeMed());
                visite.setCodePat(visiteDto.getCodePat());
                visite.setMedecin(medecin);
                visite.setPatient(patient);
                transaction = session.beginTransaction();
                session.save(visite);
                transaction.commit();
            }
        }
    }

    public VisitDetailsDto getOne(int codeVisite) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Visite visite = session.get(Visite.class, codeVisite);
            if (visite != null) {
                return getVisitDetailsDto(visite);
            }
        }
        return null;
    }

    public Set<VisitDetailsDto> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Visite> visites = session.createQuery("from Visite").list();

            return visites.stream().map(
                    this::getVisitDetailsDto
            ).collect(Collectors.toSet());
        }
    }

    public void update(int codeVisite, VisiteDto visiteDto) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction;
            Visite visite = session.get(Visite.class, codeVisite);
            if (visite != null) {
                Medecin medecin = session.get(Medecin.class, visiteDto.getCodeMed());
                Patient patient = session.get(Patient.class, visiteDto.getCodePat());
                if (medecin != null && patient != null) {
                    visite.setDateVisite(visiteDto.getDateVisite());
                    visite.setCodeMed(visiteDto.getCodeMed());
                    visite.setCodePat(patient.getCodePat());
                    visite.setMedecin(medecin);
                    visite.setPatient(patient);
                    transaction = session.beginTransaction();
                    session.update(visite);
                    transaction.commit();
                }
            }
        }
    }

    public void delete(int codeVisite) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;
            transaction = session.beginTransaction();
            Visite visite = session.get(Visite.class, codeVisite);
            if (visite != null) {
                session.delete(visite);
                transaction.commit();
            }
        }
    }


    private VisitDetailsDto getVisitDetailsDto(Visite visite) {
        VisitDetailsDto v = new VisitDetailsDto();
        v.setCodeVisite(visite.getCodeVisite());
        v.setDateVisite(visite.getDateVisite());
        v.setMedecin(modelMapper.map(visite.getMedecin(), MedecinDto.class));
        v.setPatient(modelMapper.map(visite.getPatient(), PatientDto.class));
        return v;
    }

}
