package com.launch.template.infrastructure.data.repository;

import com.launch.template.domain.exception.EntityNotFoundException;
import com.launch.template.infrastructure.data.dto.BaseDto;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;

import java.io.Serializable;

import static java.util.Optional.ofNullable;

/**
 * Provides a base implementation for data service operations on entities.
 * This abstract class is designed to be extended by specific data service implementations
 * to provide common functionality across different types of entities.
 *
 * @param <I> the type of the entity ID, must be serializable
 * @param <T> the type of the Data Transfer Object (DTO) associated with the entity
 */
@Log4j2
public abstract class BaseDataServiceImpl<I extends Serializable, T extends BaseDto<I>> {

    private final EntityManager entityManager;

    protected BaseDataServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    /**
     * Prepares the entity for update operations by ensuring non-null properties
     * are correctly maintained. Specifically, it preserves the createdAt field value
     * before any update operation is performed.
     *
     * @param entity the entity to be updated
     * @return the entity with preserved createdAt field
     * @throws EntityNotFoundException if the entity does not exist in the database
     */
    @Transactional
    protected T beforeUpdate(T entity) {
        Session session = entityManager.unwrap(Session.class);
log.error("session = {}", session);
        BaseDto<I> saved = ofNullable(session.find(entity.getClass(), entity.getId()))
                .orElseThrow(() -> new EntityNotFoundException(String.format("The entity of type: %s with ID: %s not found",
                        entity.getClass().getSimpleName(), entity.getId())));
        entity.setCreatedAt(saved.getCreatedAt());
        return entity;
    }

}
