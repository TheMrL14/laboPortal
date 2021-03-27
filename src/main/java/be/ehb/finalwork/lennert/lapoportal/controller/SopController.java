package be.ehb.finalwork.lennert.lapoportal.controller;


import be.ehb.finalwork.lennert.lapoportal.dao.SopDAO;
import be.ehb.finalwork.lennert.lapoportal.dto.SopDTO;
import be.ehb.finalwork.lennert.lapoportal.entities.SOP;
import be.ehb.finalwork.lennert.lapoportal.exceptions.EntityNotFound;
import be.ehb.finalwork.lennert.lapoportal.exceptions.InputNotCorrect;
import be.ehb.finalwork.lennert.lapoportal.mappers.Mapper;
import be.ehb.finalwork.lennert.lapoportal.mappers.SOPClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController 
@RequestMapping("/api/sops")
@CrossOrigin("*")
public class SopController {

    private final SopDAO dao;
    private final Mapper<SOP, SopDTO> map;

    @Autowired
    public SopController(SopDAO dao) {
        this.dao = dao;
        this.map = new SOPClassMapper();
    }


    @GetMapping( value = "")
    public ResponseEntity<List<SopDTO>> findAllSops(){
        List<SopDTO> sops  = map.fromEntities(dao.findAll());
        return ResponseEntity.ok().body(sops);

    }



    @GetMapping(value = "/{id}",
            produces = "application/json")
    public ResponseEntity<SopDTO> findDeviceById(@PathVariable(name = "id") Long id ) throws EntityNotFound, InputNotCorrect {
        SOP sop = findById(id);
        return ResponseEntity.status(201).body(map.fromEntity(sop));

    }

    @PutMapping(value = "/{id}",
            produces = "application/json")
    public ResponseEntity<SopDTO> editDeviceById(@PathVariable(name = "id") Long id ,@RequestBody SopDTO sopDetails ) throws EntityNotFound, InputNotCorrect {
        SOP sop = findById(id);
        sop.setFromSop(sopDetails);
        dao.save(sop);
        return  ResponseEntity.status(201).body(map.fromEntity(sop));
    }

    //POST request
    @PostMapping(value = "",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<SopDTO> addNewSop(HttpServletResponse resp,@RequestBody SopDTO newSop) {
        SOP sop = dao.save(new SOP(newSop,"TEST"));
        return  ResponseEntity.status(201).body(map.fromEntity(sop));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteSop(@PathVariable(name = "id") Long id){
        dao.deleteById(id);
    }

    private SOP findById(Long id) throws EntityNotFound, InputNotCorrect {
        if( id < 0) throw new InputNotCorrect();
        return dao.findById(id)
                .orElseThrow(EntityNotFound::new);
    }
}
