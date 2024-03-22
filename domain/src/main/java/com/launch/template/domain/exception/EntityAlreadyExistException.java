package com.launch.template.domain.exception;

public class EntityAlreadyExistException extends RuntimeException{
    private final String entityName;
    private final String value;
    private final String fieldName;

    public EntityAlreadyExistException(String entityName, String fieldName, String value){
        super(String.format("%s with %s value %s already exists", entityName, fieldName, value));
        this.entityName = entityName;
        this.fieldName = fieldName;
        this.value = value;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getValue() {
        return value;
    }
}
