package suai.labs.spring.transport.service;

public interface EntityConverter<E, V> {
    V convertToView(E entity);
}
