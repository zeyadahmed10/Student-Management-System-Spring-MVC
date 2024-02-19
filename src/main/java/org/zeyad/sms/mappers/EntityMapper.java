package org.zeyad.sms.mappers;

import java.util.List;

public interface EntityMapper<E, D> {
    D map(E entity);
    List<D> map(List<E> entities);
}
