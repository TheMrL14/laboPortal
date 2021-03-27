package be.ehb.finalwork.lennert.lapoportal.controller;


import be.ehb.finalwork.lennert.lapoportal.dao.DeviceDAO;
import be.ehb.finalwork.lennert.lapoportal.dto.DeviceDTO;
import be.ehb.finalwork.lennert.lapoportal.entities.Device;
import be.ehb.finalwork.lennert.lapoportal.exceptions.EntityNotFound;
import be.ehb.finalwork.lennert.lapoportal.exceptions.InputNotCorrect;
import be.ehb.finalwork.lennert.lapoportal.mappers.DeviceClassMapper;
import be.ehb.finalwork.lennert.lapoportal.mappers.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/devices")
@CrossOrigin("*")
public class DeviceController {

    private final DeviceDAO dao;
    private final Mapper<Device, DeviceDTO> map;
    @Autowired
    public DeviceController(DeviceDAO dao){
        this.dao = dao;
        this.map = new DeviceClassMapper();
    }


    @GetMapping( value = "")
    public ResponseEntity<List<DeviceDTO>>  findAllDevices(){
        List<DeviceDTO> devices  = map.fromEntities(dao.findAll());
        return ResponseEntity.ok().body(devices);
    }



    @GetMapping(value = "/{id}",
            produces = "application/json")
    public ResponseEntity<DeviceDTO> findDeviceById(@PathVariable(name = "id") Long id ) throws EntityNotFound, InputNotCorrect {
        Device device = findById(id);
        return ResponseEntity.status(201).body(map.fromEntity(device));
    }


    @PutMapping(value = "/{id}",
            produces = "application/json")
    public ResponseEntity<DeviceDTO> editDeviceById(@PathVariable(name = "id") Long id ,@RequestBody DeviceDTO deviceDetails ) throws EntityNotFound, InputNotCorrect {
        Device device = findById(id);
        device.setFromDevice(deviceDetails);
        return  ResponseEntity.status(201).body(map.fromEntity(device));
    }

    //POST request
    @PostMapping(value = "",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeviceDTO> addNewDevice(@RequestBody DeviceDTO newDevice) {
        Device device = dao.save(new Device(newDevice));
        return ResponseEntity.status(201).body(map.fromEntity(device));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteDevice(@PathVariable(name = "id") Long id){
        dao.deleteById(id);
    }


    private Device findById(Long id) throws EntityNotFound, InputNotCorrect {
        if( id < 0) throw new InputNotCorrect();
        return dao.findById(id)
                .orElseThrow(EntityNotFound::new);
    }
}
