package org.backend.master.springpractice.comment.util;

import org.backend.master.springpractice.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NameTagExtractorTest {

    @Test
    public void findTagsTest() {
        //Given
        String content = "@existUser 내용 @nonExistUser 내용";

        //When
        List<String> tags = NameTagExtractor.extractNameTags(content);

        //Then
        assertThat(tags).hasSize(2);
    }
}