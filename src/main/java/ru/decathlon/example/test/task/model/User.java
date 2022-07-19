package ru.decathlon.example.test.task.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.decathlon.example.test.task.util.CraftedHash;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "details")
    private UsersDetails details;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public User(String username, String password) {
        this.uuid = CraftedHash.hash();
        this.username = Objects.requireNonNull(username, "username");
        this.password = Objects.requireNonNull(password, "password");
        this.role = Role.USER;
        this.status = Status.ACTIVE;
    }

    @Override
    public String toString() {
        return getUuid() + ": " + getUsername();
    }
}
