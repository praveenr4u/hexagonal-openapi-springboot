package com.launch.template.application.query.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserQuery implements Query{
    private String username;
}
