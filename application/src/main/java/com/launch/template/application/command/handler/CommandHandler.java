package com.launch.template.application.command.handler;

public interface CommandHandler<T, U> {
    U handle(T command);
}

