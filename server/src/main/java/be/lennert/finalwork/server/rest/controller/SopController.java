package be.lennert.finalwork.server.rest.controller;


import be.lennert.finalwork.server.async.Task;
import be.lennert.finalwork.server.core.dao.SopDAO;
import be.lennert.finalwork.server.core.entities.SOP;
import be.lennert.finalwork.server.rest.dto.SopDTO;
import be.lennert.finalwork.server.rest.exceptions.EntityNotFound;
import be.lennert.finalwork.server.rest.exceptions.InputNotCorrect;
import be.lennert.finalwork.server.rest.mappers.Mapper;
import be.lennert.finalwork.server.rest.mappers.SOPClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/api/sops")
@CrossOrigin(origins = {"https://www.lennertvh.xyz", "http://localhost:3000"}, maxAge = 3600)
public class SopController {

    private final SopDAO dao;
    private final Mapper<SOP, SopDTO> map;

    private final Task<MultipartFile> pdfImportTask;

    @Autowired
    public SopController(SopDAO dao, @Qualifier("pdfImportTask") Task<MultipartFile> pdfImportTask) {
        this.dao = dao;
        this.pdfImportTask = pdfImportTask;
        this.map = new SOPClassMapper();
    }


    @GetMapping(value = "")
    public ResponseEntity<List<SopDTO>> findAllSops() {
        List<SopDTO> sops = map.fromEntities(dao.findAll());
        return ResponseEntity.ok().body(sops);

    }


    @GetMapping(value = "/{id}",
            produces = "application/json")
    public ResponseEntity<SopDTO> findDeviceById(@PathVariable(name = "id") Long id) throws EntityNotFound, InputNotCorrect {
        SOP sop = findById(id);
        return ResponseEntity.status(201).body(map.fromEntity(sop));

    }

    @PutMapping(value = "/{id}",
            produces = "application/json")
    public ResponseEntity<SopDTO> editDeviceById(@PathVariable(name = "id") Long id, @RequestBody SopDTO sopDetails) throws EntityNotFound, InputNotCorrect {
        SOP sop = findById(id);
        sop.setFromSop(sopDetails);

        return ResponseEntity.status(201).body(map.fromEntity(dao.save(sop)));
    }

    //POST request
    @PostMapping(value = "",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<SopDTO> addNewSop(HttpServletResponse resp, @RequestBody SopDTO newSop) {
        SOP sop = dao.save(new SOP(newSop));
        return ResponseEntity.status(201).body(map.fromEntity(sop));
    }

    //POST request
    @PostMapping(value = "/upload",
            produces = MediaType.APPLICATION_PDF_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<Object> addNewSopByFile(HttpServletResponse resp, @RequestParam("file") MultipartFile newFile) {
        pdfImportTask.execute(newFile);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteSop(@PathVariable(name = "id") Long id) {
        dao.deleteById(id);
    }

    private SOP findById(Long id) throws EntityNotFound, InputNotCorrect {
        if (id < 0) throw new InputNotCorrect();
        return dao.findById(id)
                .orElseThrow(EntityNotFound::new);
    }
}
