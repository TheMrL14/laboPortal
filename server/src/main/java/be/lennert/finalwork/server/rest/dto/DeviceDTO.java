package be.lennert.finalwork.server.rest.dto;

import be.lennert.finalwork.server.core.entities.SOP;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class DeviceDTO {
    private Long id;
    private String name;
    private String description;
    private SOP sop;
    private byte[] image;
    private String imageName;
    private List<String> externalLinks;
    private List<String> videoFiles;

    public void setVideoFiles(List<String> videoFiles) {
        this.videoFiles = videoFiles;
    }

    public void setSOP(SOP sop) {
        this.sop = sop;
    }
}
