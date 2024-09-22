package suai.trading.core.service;

import lombok.SneakyThrows;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractEntityConverter<E, V> implements EntityConverter<E, V> {

    @Override
    @SneakyThrows
    public V convertToView(E entity) {
        throw new UnsupportedOperationException("Not override yet");
    }

    @Override
    public List<V> convertToView(List<E> entities) {
        return Optional.ofNullable(entities)
                .map(e -> e.stream().map(this::convertToView).collect(Collectors.toList()))
                .orElse(null);
    }
}
