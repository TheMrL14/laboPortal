package be.ehb.finalwork.lennert.lapoportal.rest.mappers;


import be.ehb.finalwork.lennert.lapoportal.core.entities.SOP;
import be.ehb.finalwork.lennert.lapoportal.rest.dto.SopDTO;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SOPClassMapper implements Mapper<SOP, SopDTO> {

    @Override
    public SopDTO fromEntity(SOP e) {
        return new SopDTO(e.getId(), e.getTitle(), e.getDescription(), e.getAbbreviations(),
                e.getAuthors(), e.getRevisors(),
                e.getProcedure(),
                e.getImage(), e.getImageName());
    }
}
