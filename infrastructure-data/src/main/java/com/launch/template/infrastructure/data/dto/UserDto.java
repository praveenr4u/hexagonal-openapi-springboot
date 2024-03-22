package com.launch.template.infrastructure.data.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Entity
@Table(name = "`user`", schema = "public")
@DynamicUpdate
@EqualsAndHashCode(callSuper = true, of = {})
public class UserDto extends BaseDto<Long> {
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

}
