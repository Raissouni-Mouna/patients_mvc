package com.example.patients_mvc.web;

import com.example.patients_mvc.entities.Patient;
import com.example.patients_mvc.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    // inection de dependance autowred (pas recommande ) --> construir un constructeur avec les parametre
    private PatientRepository patientRepository; // acceder a la base de donnes
    @GetMapping(path = "/index")
    // request pqrqmetre est un parametre de l'url
    public String patients(Model model,
                           @RequestParam(name="page",defaultValue = "0") int page ,
                           @RequestParam(name="size",defaultValue = "5") int size,
                           @RequestParam(name="keyword",defaultValue = "") String keyword
                           ){
        Page<Patient> pagePatients = patientRepository.findByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listPatient",pagePatients.getContent());
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "patients";
    }
    @GetMapping( "/delete")
    public String delete (Long id, String keyword , int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping( "/")
    public String home (){
        return "redirect:/index";
    }
    @GetMapping("/formPatients")
    public String formPatients(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatients";
    }
    @PostMapping(path="/save")
    public String save(Model model , @Valid  Patient patient, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);
        return "redirect:/index";
    }

    @PostMapping(path="/edit")
    public String edit(Model model , @Valid  Patient patient, BindingResult bindingResult,
                       @RequestParam(name="page",defaultValue = "0") int page ,
                       @RequestParam(name="keyword",defaultValue = "") String keyword
    ){
        if(bindingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping( "/editPatient")
    public String editPatient (Model model,Long id, String keyword , int page){
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient==null) throw new RuntimeException("Patient introuvable");
        model.addAttribute("patient",patient);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);

        return "editPatient";
    }

    // cote client liste patient en forma json
    @GetMapping("/patient")
    @ResponseBody
    public List<Patient> listPatients(){
        return patientRepository.findAll();
    }

}
