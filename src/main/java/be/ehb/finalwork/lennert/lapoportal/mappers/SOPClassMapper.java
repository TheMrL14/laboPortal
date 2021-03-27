package be.ehb.finalwork.lennert.lapoportal.mappers;


import be.ehb.finalwork.lennert.lapoportal.dto.SopDTO;
import be.ehb.finalwork.lennert.lapoportal.entities.SOP;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SOPClassMapper implements Mapper<SOP, SopDTO> {

    @Override
    public SopDTO fromEntity(SOP e) {
        return new SopDTO(e.getId(),e.getTitle(),e.getDescription(),
                e.getAuthors(),e.getRevisors(),
                e.getProcedure());
    }
}
