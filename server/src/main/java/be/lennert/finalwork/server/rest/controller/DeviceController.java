package be.lennert.finalwork.server.rest.controller;


import be.lennert.finalwork.server.core.dao.DeviceDAO;
import be.lennert.finalwork.server.core.dao.SopDAO;
import be.lennert.finalwork.server.core.entities.Device;
import be.lennert.finalwork.server.core.entities.SOP;
import be.lennert.finalwork.server.rest.dto.DeviceDTO;
import be.lennert.finalwork.server.rest.exceptions.EntityNotFound;
import be.lennert.finalwork.server.rest.exceptions.InputNotCorrect;
import be.lennert.finalwork.server.rest.mappers.DeviceClassMapper;
import be.lennert.finalwork.server.rest.mappers.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/devices")
@CrossOrigin(origins = {"https://www.lennertvh.xyz", "http://localhost:3000", "http://193.191.183.46", "http://localhost"}, maxAge = 3600)
public class DeviceController {

    private final DeviceDAO dao;
    private final SopDAO sopDAO;
    private final Mapper<Device, DeviceDTO> map;

    @Autowired
    public DeviceController(DeviceDAO dao, SopDAO sopDAO) {
        this.dao = dao;
        this.sopDAO = sopDAO;
        this.map = new DeviceClassMapper();
    }


    @GetMapping(value = "")
    public ResponseEntity<List<DeviceDTO>> findAllDevices() {
        List<DeviceDTO> devices = map.fromEntities(dao.findAll());
        return ResponseEntity.ok().body(devices);
    }


    @GetMapping(value = "/{id}",
            produces = "application/json")
    public ResponseEntity<DeviceDTO> findDeviceById(@PathVariable(name = "id") Long id) throws EntityNotFound, InputNotCorrect {
        Device device = findById(id);
        return ResponseEntity.ok().body(map.fromEntity(device));
    }


    @PutMapping(value = "/{id}",
            produces = "application/json")
    public ResponseEntity<DeviceDTO> editDeviceById(@PathVariable(name = "id") Long id, @RequestBody DeviceDTO deviceDetails) throws EntityNotFound, InputNotCorrect {
        Device device = findById(id);
        device.setFromDevice(deviceDetails);
        dao.save(device);
        return ResponseEntity.status(201).body(map.fromEntity(device));
    }

    //POST request
    @PostMapping(value = "",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeviceDTO> addNewDevice(@RequestBody DeviceDTO newDevice) {

        if (newDevice.getSop().getTitle() != null) {
            SOP sop = sopDAO.findById(newDevice.getSop().getId()).get();
            sop.hasDevice();
            newDevice.setSOP(sop);
        }
        Device device = dao.save(new Device(newDevice));

        return ResponseEntity.status(201).body(map.fromEntity(device));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteDevice(@PathVariable(name = "id") Long id) {
        dao.deleteById(id);
    }


    private Device findById(Long id) throws EntityNotFound, InputNotCorrect {
        if (id < 0) throw new InputNotCorrect();
        return dao.findById(id)
                .orElseThrow(EntityNotFound::new);
    }
}
