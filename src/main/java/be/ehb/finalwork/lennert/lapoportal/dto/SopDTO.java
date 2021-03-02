package be.ehb.finalwork.lennert.lapoportal.dto;

import be.ehb.finalwork.lennert.lapoportal.entities.SOP;
import be.ehb.finalwork.lennert.lapoportal.entities.Step;
import be.ehb.finalwork.lennert.lapoportal.entities.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class SopDTO {

    private final String title;
    private final String description;
    private final List<Long> authors;
    private final List<Long> revisors;
    private  LocalDate lastModifiedDate;
    private final LocalDate creationDate;
    private final List<Step> procedure;

    public SopDTO(String title, String description, List<Long> authors, List<Long> revisors,  List<Step>  procedure) {
        this.title = title;
        this.description = description;
        this.authors = authors;
        this.revisors = revisors;
        this.creationDate = LocalDate.now();
        this.procedure = procedure;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public  List<Step>  getProcedure() {
        return procedure;
    }

    public List<Long> getAuthors() {
        return authors;
    }

    public List<Long> getRevisors() {
        return revisors;
    }
}
