package org.zeyad.sms.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zeyad.sms.exceptions.ResourceNotFoundException;
import org.zeyad.sms.mappers.EntityMapper;

import java.util.List;
import java.util.Optional;

public abstract class CrudService<T, ID, D> {
    protected abstract JpaRepository<T, ID> getRepository();
    protected abstract EntityMapper<T, D> getMapper();

    public List<D> getAll() {
        return getMapper().map(getRepository().findAll());
    }

    public T getById(ID id) {
        return getRepository().findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No entity found with id " + id));
    }

    public D add(T entity) {
        return getMapper().map(getRepository().save(entity));
    }

    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }

}
