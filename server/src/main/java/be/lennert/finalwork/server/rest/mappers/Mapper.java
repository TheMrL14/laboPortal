package be.lennert.finalwork.server.rest.mappers;

import be.lennert.finalwork.server.core.entities.BaseEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface Mapper<G extends BaseEntity, O> {

    O fromEntity(G entity);

    default List<O> fromEntities(Iterable<G> entities) {
        return StreamSupport
                .stream(entities.spliterator(), false)
                .map(this::fromEntity).collect(Collectors.toList());

    }
}
