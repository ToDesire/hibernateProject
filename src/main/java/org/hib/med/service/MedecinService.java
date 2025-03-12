package org.hib.med.service;


import org.hib.med.dto.MedecinDto;
import org.hib.med.entity.Medecin;
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
public class MedecinService {

    @Autowired
    private ModelMapper modelMapper;

    public void create(MedecinDto medecinDto) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction;
            Medecin medecin = modelMapper.map(medecinDto, Medecin.class);
            transaction = session.beginTransaction();
            session.save(medecin);
            transaction.commit();
        }
    }

    public MedecinDto getOne(int codeMed) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Medecin medecin = session.get(Medecin.class, codeMed);
            if (medecin != null) {
                session.close();
                return modelMapper.map(medecin, MedecinDto.class);
            }
        }
        return null;
    }

    public Set<MedecinDto> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Medecin> medecins = session.createQuery("from Medecin").list();

            return medecins.stream().map(
                    medecin -> modelMapper.map(medecin, MedecinDto.class)
            ).collect(Collectors.toSet());
        }
    }

    public void update(int codeMed, MedecinDto medecinDto) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction;
            Medecin medecin = session.get(Medecin.class, codeMed);
            if (medecin != null) {
                medecin.setDoctorFirstName(medecinDto.getDoctorFirstName());
                medecin.setDoctorLastName(medecinDto.getDoctorLastName());
                medecin.setDoctorGrade(medecinDto.getDoctorGrade());
                transaction = session.beginTransaction();
                session.update(medecin);
                transaction.commit();
            } else throw new RuntimeException("Doctor not found");
        }
    }

    public void delete(int codeMed) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction;
            transaction = session.beginTransaction();
            Medecin medecin = session.get(Medecin.class, codeMed);
            if (medecin != null) {
                session.delete(medecin);
                transaction.commit();
            }
        }
    }

}
