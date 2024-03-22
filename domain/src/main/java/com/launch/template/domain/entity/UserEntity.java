package com.launch.template.domain.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true, of = {})
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity<Long> {

    private String username;

    private String name;

    private String email;

}
