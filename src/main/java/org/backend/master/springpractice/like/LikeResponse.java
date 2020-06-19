package org.backend.master.springpractice.like;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class LikeResponse {
    private List<String> users;

    public LikeResponse(List<String> users) {
        this.users = users;
    }
}
