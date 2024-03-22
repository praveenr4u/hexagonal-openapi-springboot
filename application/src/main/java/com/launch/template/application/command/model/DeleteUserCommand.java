package com.launch.template.application.command.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DeleteUserCommand implements Command{
    private String username;
}
