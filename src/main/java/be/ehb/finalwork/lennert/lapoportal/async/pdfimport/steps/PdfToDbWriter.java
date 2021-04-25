package be.ehb.finalwork.lennert.lapoportal.async.pdfimport.steps;

import be.ehb.finalwork.lennert.lapoportal.async.Writer;
import be.ehb.finalwork.lennert.lapoportal.core.dao.DeviceDAO;
import be.ehb.finalwork.lennert.lapoportal.core.entities.Device;
import be.ehb.finalwork.lennert.lapoportal.core.entities.SOP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PdfToDbWriter implements Writer<SOP> {

    private final DeviceDAO dao;

    @Autowired
    public PdfToDbWriter(DeviceDAO dao) {
        this.dao = dao;
    }

    @Override
    public void write(SOP output) {
        String deviceName = output.getTitle().substring(output.getTitle().lastIndexOf("-") + 1);
        Device device = dao.findByName(deviceName);
        if (device == null) {
            device = Device.builder()
                    .name(deviceName)
                    .sop(output)
                    .build();
        } else {
            device.setSop(output);
        }
        dao.save(device);
    }
}
