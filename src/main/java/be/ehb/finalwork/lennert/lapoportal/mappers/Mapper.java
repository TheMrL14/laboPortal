package be.ehb.finalwork.lennert.lapoportal.mappers;

import be.ehb.finalwork.lennert.lapoportal.entities.BaseEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface Mapper<G extends BaseEntity,O >  {

        public abstract O fromEntity(G entity);

        public default List<O> fromEntities(Iterable<G> entities) {
          return StreamSupport
                    .stream(entities.spliterator(), false)
                    .map(this::fromEntity).collect(Collectors.toList());

        }
}
