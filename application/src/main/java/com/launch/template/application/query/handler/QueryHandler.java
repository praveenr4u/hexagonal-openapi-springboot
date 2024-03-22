package com.launch.template.application.query.handler;

public interface QueryHandler<Q, R>{
    R handle(Q query);
}

