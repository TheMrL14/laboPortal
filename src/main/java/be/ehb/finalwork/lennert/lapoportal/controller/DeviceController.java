package be.ehb.finalwork.lennert.lapoportal.controller;


import be.ehb.finalwork.lennert.lapoportal.dao.DeviceDAO;
import be.ehb.finalwork.lennert.lapoportal.dao.SopDAO;
import be.ehb.finalwork.lennert.lapoportal.entities.Device;
import be.ehb.finalwork.lennert.lapoportal.entities.SOP;
import be.ehb.finalwork.lennert.lapoportal.exceptions.EntityNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/api/devices")
@CrossOrigin("http://localhost:3000")
public class DeviceController {

    private final DeviceDAO dao;

    @Autowired
    public DeviceController(DeviceDAO dao){
        this.dao = dao;
    }

    //TODO use DTO
    @GetMapping( value = "")
    @ResponseBody
    public Iterable<Device> findAllDevices(){
        return dao.findAll();
    }


    //TODO use DTO
    @GetMapping(value = "/{id}",
            produces = "application/json")
    public Optional<Device> findDeviceById(@PathVariable(name = "id") Long id ) throws EntityNotFound {
        return Optional.ofNullable(dao.findById(id))
                .orElseThrow(EntityNotFound::new);

    }

    //POST request
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void addNewDevice(HttpServletResponse resp, @RequestBody Device newDevice) throws IOException, EntityNotFound {
        Optional.ofNullable(dao.save(newDevice))
                .orElseThrow(EntityNotFound::new);
        resp.setStatus(201);

    }

    @DeleteMapping(value = "/{id}")
    public void deleteDevice(HttpServletResponse resp ,@PathVariable(name = "id") Long id){
        dao.deleteById(id);
    }
}
