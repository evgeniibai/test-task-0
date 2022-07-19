package ru.decathlon.example.test.task.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.decathlon.example.test.task.model.Status;
import ru.decathlon.example.test.task.model.User;

import java.util.Collection;

public record SecurityUser(User user) implements UserDetails {

    private static final String NOT_ACTIVE = Status.NOT_ACTIVE.name();
    private static final String BANNED = Status.BANNED.name();
    private static final String DELETED = Status.DELETED.name();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRole().getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        String userStatus = user.getStatus().name();
        return !userStatus.equals(NOT_ACTIVE);
    }

    @Override
    public boolean isAccountNonLocked() {
        String userStatus = user.getStatus().name();
        return !userStatus.equals(BANNED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        String userStatus = user.getStatus().name();
        return !userStatus.equals(NOT_ACTIVE);
    }

    @Override
    public boolean isEnabled() {
        String userStatus = user.getStatus().name();
        return !userStatus.equals(DELETED);
    }
}
