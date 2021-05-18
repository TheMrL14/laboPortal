package be.lennert.finalwork.server.rest.dto;

import be.lennert.finalwork.server.core.entities.Abbreviation;
import be.lennert.finalwork.server.core.entities.SOP.SopType;
import be.lennert.finalwork.server.core.entities.Step;
import be.lennert.finalwork.server.rest.security.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
public class SopDTO {
    private Long id;
    private String title;
    private String description;
    private List<Abbreviation> abbreviations;
    private List<User> authors;
    private List<User> revisors;
    private List<Step> procedure;
    private byte[] image;
    private String imageName;
    private LocalDate creationDate;
    private SopType type;


    public SopDTO(Long id, String title, String description, List<Abbreviation> abbreviations, List<User> authors, List<User> revisors, List<Step> procedure, byte[] image, String imageName, LocalDate creationDate, SopType type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.abbreviations = abbreviations;
        this.authors = authors;
        this.revisors = revisors;
        this.procedure = procedure;
        this.image = image;
        this.imageName = imageName;
        this.creationDate = creationDate;
        this.type = type;
    }
}
