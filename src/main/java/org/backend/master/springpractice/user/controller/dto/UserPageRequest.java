package org.backend.master.springpractice.user.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserPageRequest {
    public static final int PAGE_SIZE_LIMIT = 25;
    public static final int PAGE_SIZE_DEFAULT = 10;

    private int page;
    private int size = PAGE_SIZE_DEFAULT;

    public Pageable getPageable() {
        return PageRequest.of(page, Math.min(size, PAGE_SIZE_LIMIT), Sort.by(Sort.Direction.ASC, "id"));
    }
}
