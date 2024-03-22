package com.launch.template.application.query.handler;

import com.launch.template.application.query.model.UsersQuery;
import com.launch.template.domain.entity.UserEntity;
import java.util.List;

import com.launch.template.domain.repository.UserDataService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersQueryHandler extends QueryHandlerBase<UsersQuery, List<UserEntity>> {
    final Logger logger = LogManager.getLogger(UsersQueryHandler.class);

    private final UserDataService userRepository;

    @Override
    public List<UserEntity> handle(UsersQuery query) {
        logger.info("Started handling the query request");
        List<UserEntity> userEntities = userRepository.getAll();
        logger.info("Completed handling the query request");
        return userEntities;
    }
}
