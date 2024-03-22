package com.launch.template.infrastructure.data.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

/**
 * Provides a base data transfer object (DTO) structure for entities, including common fields and lifecycle callbacks.
 * <p>
 * This abstract class defines the foundation for entity objects with automatic handling of audit fields such as
 * {@code createdAt} and {@code updatedAt}. It includes the primary key {@code id}, and manages audit timestamps to
 * reflect creation and last update times.
 * <p>
 * The {@code beforeSave} method, marked with {@code @PrePersist}, ensures that {@code createdAt} and {@code updatedAt}
 * are properly set before persisting the entity, promoting consistency and integrity across all extending entities.
 */
@Data
@EqualsAndHashCode(of = {"id"})
@MappedSuperclass
@DynamicUpdate
public abstract class BaseDto <T extends Serializable> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Date createdAt;
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Date updatedAt;

/*
    @PreUpdate
    private void beforeUpdate() {
        JpaRepository<? extends BaseDto, Object> repository = SpringContextBridge.getRepositoryBean(this.getClass());
        BaseDto dto = repository.findById(getId()).get();
        createdAt = dto.getCreatedAt();
    }
*/

}
