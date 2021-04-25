package be.ehb.finalwork.lennert.lapoportal.rest.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @GetMapping(value = {"/", ""})
    public String helloWorld() {
        return "hello world";
    }
}
