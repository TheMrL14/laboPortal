package be.lennert.finalwork.server.rest.mappers;


import be.lennert.finalwork.server.core.entities.SOP;
import be.lennert.finalwork.server.rest.dto.SopDTO;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor
public class SOPClassMapper implements Mapper<SOP, SopDTO> {

    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public SopDTO fromEntity(SOP e) {
        return new SopDTO(e.getId(), e.getTitle(), e.getDescription(), e.getAbbreviations(),
                e.getAuthors(), e.getRevisors(),
                e.getProcedure(),
                e.getImage(), e.getImageName(), e.getCreationDate().format(dateFormat));
    }
}
