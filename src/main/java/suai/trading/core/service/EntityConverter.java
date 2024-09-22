package suai.trading.core.service;

import java.util.List;

public interface EntityConverter<E, V> {

    V convertToView(E entity);
    List<V> convertToView(List<E> entities);
}
