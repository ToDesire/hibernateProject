package org.hib.med.service;

import org.hib.med.dto.PatientDto;
import org.hib.med.entity.Patient;
import org.hib.med.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private ModelMapper modelMapper;

    public void create(PatientDto patientDto) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction;
            Patient patient = modelMapper.map(patientDto, Patient.class);
            transaction = session.beginTransaction();
            session.save(patient);
            transaction.commit();
        }
    }

    public PatientDto getOne(String codePat) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Patient patient = session.get(Patient.class, codePat);
            if (patient != null) {
                return modelMapper.map(patient, PatientDto.class);
            }
        }
        return null;
    }

    public Set<PatientDto> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Patient> patients = session.createQuery("from Patient").list();
            return patients.stream().map(
                    patient -> modelMapper.map(patient, PatientDto.class)
            ).collect(Collectors.toSet());
        }
    }

    public void update(String codePat, PatientDto patientDto) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction;
            Patient patient = session.get(Patient.class, codePat);
            if (patient != null) {
                patient.setPatientFirstName(patientDto.getPatientFirstName());
                patient.setPatientLastName(patientDto.getPatientLastName());
                patient.setAddress(patientDto.getAddress());
                patient.setSex(patientDto.getSex());
                transaction = session.beginTransaction();
                session.update(patient);
                transaction.commit();
            } else throw new RuntimeException("Doctor not found");
        }
    }

    public void delete(String codePat) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction;
            transaction = session.beginTransaction();
            Patient patient = session.get(Patient.class, codePat);
            if (patient != null) {
                session.delete(patient);
                transaction.commit();
            }
        }
    }

    public Set<PatientDto> findByCodePatOrPatientLastNameOrPatientFirstName(String term) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String searchTerm = "%" + term.toLowerCase() + "%";
            Query<Patient> query = session.createQuery(
                    "from Patient p where lower(p.codePat) like :searchTerm" +
                    " or lower(p.patientLastName) like :searchTerm" +
                    " or lower(p.patientFirstName) like :searchTerm",
                    Patient.class
            );
            query.setParameter("searchTerm", searchTerm);
            List<Patient> patients = query.list();
            return patients.stream().map(
                    patient -> modelMapper.map(patient, PatientDto.class)
            ).collect(Collectors.toSet());
        }
    }

}
