package org.hib.med.config;

import jakarta.annotation.PostConstruct;
import org.hib.med.enums.PatientGender;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hib.med.entity.Medecin;
import org.hib.med.entity.Patient;
import org.hib.med.entity.Visite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class DataSeeder {

    private final SessionFactory sessionFactory;

    @Autowired
    public DataSeeder(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @PostConstruct
    public void seedData() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Random rand = new Random();
        try {
            for (int i = 1; i <= 5; i++) {
                Medecin med = new Medecin();
                med.setDoctorFirstName("MedecinNom" + i);
                med.setDoctorLastName("MedecinPrenom" + i);
                med.setDoctorGrade("Grade" + ((i % 3) + 1));
                session.save(med);
            }


            for (int i = 1; i <= 30; i++) {
                Patient pat = new Patient();
                pat.setCodePat("PAT" + i);
                pat.setPatientFirstName("PatientNom" + i);
                pat.setPatientLastName("PatientPrenom" + i);
                pat.setGender(PatientGender.valueOf(i % 2 == 0 ? PatientGender.HOMME.name() : PatientGender.FEMME.name()));
                pat.setAddress(generateRandomAddress(rand));
                session.save(pat);
            }



            for (int i = 1; i <= 100; i++) {
                Visite v = new Visite();
                v.setDateVisite(new Date());

                int medId = rand.nextInt(5) + 1;
                String patCode = "PAT" + (rand.nextInt(30) + 1);

                Medecin med = session.get(Medecin.class, medId);
                Patient pat = session.get(Patient.class, patCode);

                v.setMedecin(med);
                v.setPatient(pat);

                session.save(v);
            }


            tx.commit();
            System.out.println("✔ Sample data inserted.");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static String generateRandomAddress(Random rand) {
        String[] places = {
                "Antananarivo", "Toamasina", "Fianarantsoa", "Mahajanga",
                "Toliara", "Antsiranana", "Ambatondrazaka", "Manakara",
                "Antsirabe", "Morondava"
        };

        int num1 = rand.nextInt(100);
        char letter = (char) ('A' + rand.nextInt(26));
        int num2 = rand.nextInt(100);
        String place = places[rand.nextInt(places.length)];

        return "Lot " + num1 + letter + num2 + " " + place;
    }


}
