package be.ehb.finalwork.lennert.lapoportal.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
    
    @GetMapping(value = {"/",""})
    public String helloWorld(){
        return "hello world";
    }
}
