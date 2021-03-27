package be.ehb.finalwork.lennert.lapoportal.dto;

import be.ehb.finalwork.lennert.lapoportal.entities.Step;
import be.ehb.finalwork.lennert.lapoportal.entities.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class SopDTO {
    private final Long id;
    private final  String title;
    private final String description;
    private final List<User> authors;
    private final List<User> revisors;
    private final List<Step> procedure;

}
