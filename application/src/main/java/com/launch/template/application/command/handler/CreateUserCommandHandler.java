package com.launch.template.application.command.handler;

import com.launch.template.application.command.model.CreateUserCommand;
import com.launch.template.domain.entity.UserEntity;
import com.launch.template.domain.repository.UserDataService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserCommandHandler extends CommandHandlerBase<CreateUserCommand, UserEntity> {

    final Logger logger = LogManager.getLogger(CreateUserCommandHandler.class);

    private final UserDataService userRepository;

    @Override
    public UserEntity handle(CreateUserCommand command) {
        logger.info("Started processing user");
        UserEntity user = UserEntity.builder()
                .username(command.getUsername())
                .name(command.getName())
                .email(command.getEmail())
                .build();
        UserEntity createdUser = userRepository.add(user);
        logger.info("Competed processing user");
        return createdUser;
    }
}
