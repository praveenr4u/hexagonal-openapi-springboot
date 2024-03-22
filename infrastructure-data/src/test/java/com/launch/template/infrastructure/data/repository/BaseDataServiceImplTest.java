package com.launch.template.infrastructure.data.repository;

import com.launch.template.infrastructure.data.dto.BaseDto;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BaseDataServiceImplTest {

    private static class TestClass extends BaseDataServiceImpl {
        public TestClass(EntityManager entityManager) {
            super(entityManager);
        }
    }

    private static class Dto extends BaseDto<Integer> {
        Integer id = 1;
    }
    @Mock
    EntityManager entityManager;

    @Mock
    private Session session;

    private TestClass service;

    @BeforeEach
    void before() {
        service = new TestClass(entityManager);
    }

    @Test
    public void testPreUpdate() throws InterruptedException {
        Dto saved = new Dto();
        saved.setCreatedAt(new Date());
        Dto entity = new Dto();
        when(entityManager.unwrap(Session.class)).thenReturn(session);
        when(session.find(Dto.class, entity.getId())).thenReturn(saved);
        assertNull(entity.getCreatedAt());
        service.beforeUpdate(entity);
        assertEquals(saved.getCreatedAt(), entity.getCreatedAt());
    }


}