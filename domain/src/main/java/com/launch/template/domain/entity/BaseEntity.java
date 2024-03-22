package com.launch.template.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@SuperBuilder
    @Data
    @EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
    public abstract  class BaseEntity <T extends Serializable> {
        private T id;
        private Date createdAt;
        private Date updatedAt;

  }
