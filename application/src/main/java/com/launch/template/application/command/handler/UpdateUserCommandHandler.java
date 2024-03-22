package com.launch.template.application.command.handler;

import com.launch.template.application.command.model.UpdateUserCommand;
import com.launch.template.domain.entity.UserEntity;
import com.launch.template.domain.repository.UserDataService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserCommandHandler extends CommandHandlerBase<UpdateUserCommand, UserEntity> {

    final Logger logger = LogManager.getLogger(UpdateUserCommandHandler.class);

    private final UserDataService userRepository;

    @Override
    public UserEntity handle(UpdateUserCommand command) {
        logger.info("Started processing user");
        UserEntity existingUser = userRepository.getUserByUsername(command.getUsername());
        UserEntity user = UserEntity.builder()
                .username(command.getUsername())
                .name(command.getName())
                .email(command.getEmail())
                .id(existingUser.getId())
                .build();
        UserEntity createdUser = userRepository.update(user);
        logger.info("Competed processing user");
        return createdUser;

    }

}
