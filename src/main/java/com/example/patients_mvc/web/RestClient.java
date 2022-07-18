package com.example.patients_mvc.web;

import com.example.patients_mvc.entities.Patient;
import com.example.patients_mvc.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.*;

@Controller
@AllArgsConstructor

public class RestClient {
    private static final String get_all_users = "http://localhost:8000/api/v1/users";
    private static final String get_all_users_by_id = "http://localhost:8000/users/{id}";
    private static final String Create_User_API = "http://localhost:8000/api/v1/users";
    private static final String Update_User_API = "http://localhost:8000/api/v1/users/{id}";
    private static final String Delete_User_Api = "http://localhost:8000/api/v1/users/{id}";
    private PatientRepository patientRepository; // acceder a la base de donnes

    static RestTemplate restTemplate = new RestTemplate();
    public static void main(String[] args){
        List<Integer> liste = new ArrayList<>();
        liste.add(1);
        liste.add(2);
        liste.add(3);
        liste.add(4);

// trie la liste en plaçant en premier les nombres pairs
        liste.sort((e1, e2) -> (e1 % 2) - (e2 % 2));
        System.out.println((1 % 2) - (1 % 2));
    }

    private static void callgetAlluserApi(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters",headers);

        ResponseEntity<String> result = restTemplate.exchange(get_all_users, HttpMethod.GET,entity,String.class);
        if(result.getStatusCode()== HttpStatus.OK){
            System.out.println(result);
        }
        else{
            System.out.println("error");

        }

    }

    private static void callgetAlluserApiById(){
        Map<String , String> param = new HashMap<>();
        param.put("id","uuid4");

        Patient patient = restTemplate.getForObject(get_all_users_by_id,Patient.class,param);
        System.out.println(patient.getNom());


    }

    @PostMapping(path="/saveApi")
    public String save(Model model , @Valid Patient patient, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);
        return "redirect:/index";
    }

    @GetMapping( "/oo")
    public String home2 (){
        callgetAlluserApi();
        return "redirect:/index";
    }


}
