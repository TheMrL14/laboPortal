package be.lennert.finalwork.server.rest.dto;

import be.lennert.finalwork.server.core.entities.Abbreviation;
import be.lennert.finalwork.server.core.entities.Step;
import be.lennert.finalwork.server.rest.security.entities.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class SopDTO {
    private final Long id;
    private final String title;
    private final String description;
    private final List<Abbreviation> abbreviations;
    private final List<User> authors;
    private final List<User> revisors;
    private final List<Step> procedure;
    private final byte[] image;
    private final String imageName;
    private String creationDate;

    public SopDTO(Long id, String title, String description, List<Abbreviation> abbreviations, List<User> authors, List<User> revisors, List<Step> procedure, byte[] image, String imageName, String creationDate) {
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
    }
}
