package com.launch.template.service.delegate;

import com.launch.template.api.UsersApiDelegate;
import com.launch.template.application.command.handler.CreateUserCommandHandler;
import com.launch.template.application.command.handler.DeleteUserCommandHandler;
import com.launch.template.application.command.handler.UpdateUserCommandHandler;
import com.launch.template.application.command.model.CreateUserCommand;
import com.launch.template.application.command.model.DeleteUserCommand;
import com.launch.template.application.command.model.UpdateUserCommand;
import com.launch.template.application.query.handler.UserQueryHandler;
import com.launch.template.application.query.handler.UsersQueryHandler;
import com.launch.template.application.query.model.UserQuery;
import com.launch.template.application.query.model.UsersQuery;
import com.launch.template.model.CreateUserRequest;
import com.launch.template.model.UpdateUserRequest;
import com.launch.template.model.User;
import com.launch.template.service.mapper.UserMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UsersApiDelegateImpl implements UsersApiDelegate {
    private final Logger logger = LogManager.getLogger(UsersApiDelegateImpl.class);

    private final UserQueryHandler userQueryHandler;
    private final UsersQueryHandler usersQueryHandler;

    private final CreateUserCommandHandler createUserCommandHandler;

    private final UpdateUserCommandHandler updateUserCommandHandler;

    private final DeleteUserCommandHandler deleteUserCommandHandler;

    private final UserMapper userMapper;

    @Override
    public ResponseEntity<User> createUser(CreateUserRequest user) {
        logger.info("Creating user: {}", user.getUsername());
        CreateUserCommand createUserCommand = CreateUserCommand.builder()
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .build();
        User userResponse = userMapper.userFromDomainToResponse(createUserCommandHandler.handle(createUserCommand));
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUser(String username) {
        logger.info("Getting details for user: {}",username);
        UserQuery userQuery = UserQuery.builder()
                .username(username)
                .build();
        User userResponse = userMapper.userFromDomainToResponse(userQueryHandler.handle(userQuery));
        return new ResponseEntity<>(userResponse, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("Getting all users");
        UsersQuery usersQuery = UsersQuery.builder()
                .build();
        List<User> userslist = userMapper.usersFromDomainToResponse(usersQueryHandler.handle(usersQuery));
        return new ResponseEntity<>(userslist, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> updateUser(String username, UpdateUserRequest updateUserRequest) {
        logger.info("Updating details for user: {}", username);
        UpdateUserCommand updateUserCommand = UpdateUserCommand.builder()
                .username(username)
                .name(updateUserRequest.getName())
                .email(updateUserRequest.getEmail())
                .build();
        User userResponse = userMapper.userFromDomainToResponse(updateUserCommandHandler.handle(updateUserCommand));
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> deleteUser(String username) {
        logger.info("Deleting user: {}", username);
        DeleteUserCommand deleteUserCommand = DeleteUserCommand.builder()
                .username(username)
                .build();
        User userResponse = userMapper.userFromDomainToResponse(deleteUserCommandHandler.handle(deleteUserCommand));
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

}
