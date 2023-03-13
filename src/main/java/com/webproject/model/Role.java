package com.webproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Table(name = "roles")
@Entity
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    public enum RoleEnum {
        USER,
        ADMIN
    }

    @Override
    public String getAuthority() {
        return name.name();
    }
}
