package org.backend.master.springpractice.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@ToString
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
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

