package org.backend.master.springpractice.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@NoArgsConstructor
@ToString
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 1)
    @Column(nullable = false)
    private String email;
    @Length(min = 1)
    @Column(nullable = false)
    private String password;
    @Length(min = 1)
    @Column(nullable = false)
    private String name;

    @Builder
    private User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void update(User userToUpdate) {
        this.id = userToUpdate.id;
        this.email = userToUpdate.email;
        this.password = userToUpdate.password;
        this.name = userToUpdate.name;
    }

    public boolean matchPassword(String encryptedPassword) {
        return password.equals(encryptedPassword);
    }
}

