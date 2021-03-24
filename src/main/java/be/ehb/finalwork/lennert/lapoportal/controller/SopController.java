package be.ehb.finalwork.lennert.lapoportal.controller;


import be.ehb.finalwork.lennert.lapoportal.dao.SopDAO;
import be.ehb.finalwork.lennert.lapoportal.entities.SOP;
import be.ehb.finalwork.lennert.lapoportal.entities.User;
import be.ehb.finalwork.lennert.lapoportal.exceptions.EntityNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;


@RestController 
@RequestMapping("/api/sops")
@CrossOrigin("*")
public class SopController {

    private final SopDAO dao;

    @Autowired
    public SopController(SopDAO dao){
        this.dao = dao;
    }

    //TODO use DTO
    @GetMapping( value = "")
    @ResponseBody
    public Iterable<SOP> findAllSops(){
        return dao.findAll();
    }


    //TODO use DTO
    @GetMapping(value = "/{id}")
    public SOP findSopById(@PathVariable(name = "id") Long id ) throws EntityNotFound {
        Optional<SOP> returnSop =  Optional.ofNullable(dao.findById(id)).orElseThrow(EntityNotFound::new);
        return returnSop.get();
    }

    //POST request
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SOP addNewSop(HttpServletResponse resp,@RequestBody SOP newSop) throws IOException, EntityNotFound {
        newSop.setCreationDate(LocalDate.now());
        //TEMP
        var lennert = new User();
        lennert.setId(1L);
        newSop.addAuthor(lennert);
        newSop.getProcedure().forEach(step -> step.setSop(newSop));
        SOP sop = Optional.ofNullable(dao.save(newSop))
                .orElseThrow(EntityNotFound::new);
        resp.setStatus(201);

        return sop;
    }

    @DeleteMapping(value = "/{id}")
    public void deleteSop(HttpServletResponse resp ,@PathVariable(name = "id") Long id){
        dao.deleteById(id);
    }
}
