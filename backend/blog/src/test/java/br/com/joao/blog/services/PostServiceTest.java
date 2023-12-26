package br.com.joao.blog.services;

import br.com.joao.blog.domain.post.Post;
import br.com.joao.blog.domain.post.PostDTO;
import br.com.joao.blog.repositories.PostRespository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRespository postRespository;

    @InjectMocks
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
    @DisplayName("Should show return a specific post")
    void findPostById() {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Hello, world!");

        when(postRespository.findById(1L)).thenReturn(Optional.of(post));

        Post result = postService.findPostById(post.getId());

        assertEquals(result, post);
        
    }

    @Test
    @DisplayName("Should return all posts")
    void canFindAllPosts() {
        postService.findAllPosts();

        verify(postRespository).findAll();
    }

    @Test
    @DisplayName("Should return specific posts")
    void findPostByTitle() {
        Post post = new Post();
        post.setId(1L); post.setTitle("Hello, world!");

        when(postRespository.findPostsByTitleContainingIgnoreCase(post.getTitle()))
                .thenReturn(List.of(post));

        List<Post> result = postService.findPostByTitle(post.getTitle());

        verify(postRespository).findPostsByTitleContainingIgnoreCase(post.getTitle());

        assertEquals(List.of(post), result);

    }

    @Test
    @DisplayName("Should edit the Post")
    void editPost() {
        Post post = new Post();
        post.setId(1L); post.setTitle("Hello, world");

        when(postRespository.findById(post.getId())).thenReturn(Optional.of(post));
        when(postRespository.save(post)).thenReturn(post);

        Post newPost = new Post();
        newPost.setTitle("New title!");

        Post editedPost = postService.editPost(post.getId(), newPost);


        assertThat(post).isEqualTo(editedPost);
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