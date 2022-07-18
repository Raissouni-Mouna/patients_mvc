package com.example.patients_mvc;

import com.example.patients_mvc.entities.Patient;
import com.example.patients_mvc.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {
// annotation autowired permet l'injection de dependances Cela élimine le besoin de getters et de setters.
    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcApplication.class, args);
    }
    //@Bean // pour s'executer au demarrage
    /*CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient( null, "hassan", new Date(),false,112));
            patientRepository.save(new Patient( null, "mouna", new Date(),false,182));
            patientRepository.save(new Patient( null, "hala", new Date(),true,215));
            patientRepository.save(new Patient( null, "amr", new Date(),false,112));

            patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });


        };
    }
*/
}
