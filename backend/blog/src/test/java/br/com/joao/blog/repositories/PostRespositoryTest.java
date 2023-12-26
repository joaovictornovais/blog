package br.com.joao.blog.repositories;

import br.com.joao.blog.domain.post.Post;
import br.com.joao.blog.domain.post.PostDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PostRespositoryTest {

    @Autowired
    PostRespository postRespository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get Posts successfully from DB")
    void findPostsByTitleContainingIgnoreCaseSuccess() {
        PostDTO data = new PostDTO(
                "Hello world",
                "This my first post!",
                LocalDateTime.now(),
                "Testting my first post!"
        );
        createPost(data);

        List<Post> posts = postRespository.findPostsByTitleContainingIgnoreCase(data.title());

        assertThat(posts.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("Should not get Posts from DB")
    void findPostsByTitleContainingIgnoreCaseFalse() {
        List<Post> posts = postRespository.findPostsByTitleContainingIgnoreCase("testr");

        assertThat(posts.isEmpty()).isTrue();
    }

    private Post createPost(PostDTO data) {
        Post newPost = new Post(data);
        entityManager.persist(newPost);
        return newPost;
    }
}