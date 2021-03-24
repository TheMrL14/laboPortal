package be.ehb.finalwork.lennert.lapoportal.controller;


import be.ehb.finalwork.lennert.lapoportal.dao.DeviceDAO;
import be.ehb.finalwork.lennert.lapoportal.dao.SopDAO;
import be.ehb.finalwork.lennert.lapoportal.entities.Device;
import be.ehb.finalwork.lennert.lapoportal.entities.SOP;
import be.ehb.finalwork.lennert.lapoportal.exceptions.EntityNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/api/devices")
@CrossOrigin("*")
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
    public ResponseEntity<Device> findDeviceById(@PathVariable(name = "id") Long id ) throws EntityNotFound {
        Device device = dao.findById(id)
                .orElseThrow(EntityNotFound::new);
        return ResponseEntity.status(201).body(device);

    }

    //TODO use DTO
    @PutMapping(value = "/{id}",
            produces = "application/json")
    public ResponseEntity<Device> EditDeviceById(@PathVariable(name = "id") Long id ,@RequestBody Device deviceDetails ) throws EntityNotFound {
        Device device = dao.findById(id)
                .orElseThrow(EntityNotFound::new);
        device.setFromDevice(deviceDetails);
        return  ResponseEntity.status(201).body(device);
    }

    //POST request
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Device> addNewDevice(@RequestBody Device newDevice) throws IOException, EntityNotFound {
        Device device = dao.save(newDevice);
        return ResponseEntity.status(201).body(device);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteDevice(HttpServletResponse resp ,@PathVariable(name = "id") Long id){
        dao.deleteById(id);
    }
}
