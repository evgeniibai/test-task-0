package ru.decathlon.example.test.task.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum Role {
    USER(Set.of(Permission.READ)),
    ADMIN(Set.of(Permission.READ, Permission.WRITE));

    private final Set<Permission> permissions;

    public Set<GrantedAuthority> getAuthorities() {
        return permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getAction()))
                .collect(Collectors.toSet());
    }
}
