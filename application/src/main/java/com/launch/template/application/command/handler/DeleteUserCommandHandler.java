package com.launch.template.application.command.handler;

import com.launch.template.application.command.model.DeleteUserCommand;
import com.launch.template.domain.entity.UserEntity;
import com.launch.template.domain.repository.UserDataService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserCommandHandler extends CommandHandlerBase<DeleteUserCommand, UserEntity> {

    final Logger logger = LogManager.getLogger(DeleteUserCommandHandler.class);

    private final UserDataService userRepository;

    @Override
    public UserEntity handle(DeleteUserCommand command) {
        logger.info("Started processing user");
        UserEntity userEntity = userRepository.delete(command.getUsername());
        logger.info("Competed processing user");
        return userEntity;
    }
}
