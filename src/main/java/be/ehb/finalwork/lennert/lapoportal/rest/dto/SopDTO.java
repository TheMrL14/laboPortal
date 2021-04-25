package be.ehb.finalwork.lennert.lapoportal.rest.dto;

import be.ehb.finalwork.lennert.lapoportal.core.entities.Abbreviation;
import be.ehb.finalwork.lennert.lapoportal.core.entities.Step;
import be.ehb.finalwork.lennert.lapoportal.rest.security.entities.User;
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

}
