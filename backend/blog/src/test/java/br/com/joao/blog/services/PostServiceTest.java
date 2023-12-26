package br.com.joao.blog.services;

import br.com.joao.blog.domain.post.Post;
import br.com.joao.blog.domain.post.PostDTO;
import br.com.joao.blog.repositories.PostRespository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRespository postRespository;
    private PostService postService;

    @BeforeEach
    void setUp() {
        postService = new PostService(postRespository);
    }

    @Test
    @DisplayName("Should create a new Post")
    void canCreatePost() {
        PostDTO data = new PostDTO("Hello, world", "This is my first post", LocalDateTime.now(), "Welcome to my blog!");

        postService.createPost(new Post(data));

        ArgumentCaptor<Post> postArgumentCaptor = ArgumentCaptor.forClass(Post.class);

        verify(postRespository)
                .save(postArgumentCaptor.capture());

        Post capturedPost = postArgumentCaptor.getValue();

        assertThat(capturedPost).isEqualTo(new Post(data));
    }

    @Test
    @Disabled
    void findPostById() {

    }

    @Test
    @DisplayName("Should return all posts")
    void canFindAllPosts() {
        postService.findAllPosts();

        verify(postRespository).findAll();
    }

    @Test
    @Disabled
    void findPostByTitle() {
    }

    @Test
    @Disabled
    void editPost() {
        Post data = new Post(1L, "Hello, world", "This is my first post", LocalDateTime.now(), "Welcome to my blog!");
        postService.createPost(data);

        ArgumentCaptor<Post> postArgumentCaptor = ArgumentCaptor.forClass(Post.class);

        PostDTO newPost = new PostDTO("Hellom again", "Just fixing!", LocalDateTime.now(), "Welcome to my blog!");
        postService.editPost(data.getId(), new Post(newPost));

        verify(postRespository).save(postArgumentCaptor.getValue());

        assertThat(data).isNotEqualTo(postArgumentCaptor.getValue());
    }

    @Test
    @DisplayName("Should delete a Post")
    void deletePostById() {
        Post data = new Post(1L, "Hello, world", "This is my first post", LocalDateTime.now(), "Welcome to my blog!");
        postService.createPost(data);

        postService.deletePostById(data.getId());

        verify(postRespository).deleteById(data.getId());

        try {
            assertThat(postService.findPostById(data.getId())).isNull();
        } catch (RuntimeException ignored) {
            System.out.println("Post deleted successfully!");
        }

    }
}