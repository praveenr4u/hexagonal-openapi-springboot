package com.launch.template.application.command.handler;

import com.launch.template.application.command.model.DeleteUserCommand;
import com.launch.template.domain.entity.UserEntity;
import com.launch.template.domain.repository.UserDataService;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeleteUserCommandHandlerTest {
    // Mock the dependencies of the class under test using Mockito or Spring
    @Mock
    private UserDataService userRepository = Mockito.mock(UserDataService.class);

    // Create a new instance of the class under test with the mocked dependencies injected into it using Spring or Mockito
    private final DeleteUserCommandHandler deleteUserCommandHandler = new DeleteUserCommandHandler(userRepository);

    @Test
    void handle() {
        // Arrange
        // Create the input for the method under test
        DeleteUserCommand command = DeleteUserCommand.builder()
                .username("username")
                .build();

        // Create the expected output for the method under test
        UserEntity expectedUser = UserEntity.builder()
                .username(command.getUsername())
                .build();
        // Mock the behavior of the dependencies of the class under test
        when(userRepository.delete(command.getUsername())).thenReturn(expectedUser);
        // Act
        // Call the method under test
        UserEntity actualUser = deleteUserCommandHandler.handle(command);
        // Assert
        // Verify the output of the method under test
        assertEquals(expectedUser, actualUser);
        // Verify the behavior of the dependencies of the class under test
        verify(userRepository).delete(command.getUsername());

    }

}