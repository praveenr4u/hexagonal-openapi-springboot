package com.launch.template.domain.repository;

import com.launch.template.domain.entity.UserEntity;
import com.launch.template.domain.exception.EntityNotFoundException;

import java.util.List;

public interface UserDataService {
    UserEntity getUserByUsername(String username);
    UserEntity add(UserEntity user);

    UserEntity update(UserEntity user);

    UserEntity delete(String username);
    List<UserEntity> getAll();
}
