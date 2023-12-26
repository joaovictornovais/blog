package br.com.joao.blog.controllers;

import br.com.joao.blog.domain.post.Post;
import br.com.joao.blog.domain.post.PostDTO;
import br.com.joao.blog.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostDTO postDTO) {
        return ResponseEntity.ok(postService.createPost(new Post(postDTO)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findPostById(id));
    }

    @GetMapping(params = "title")
    public ResponseEntity<List<Post>> findPostsByTitle(@RequestParam String title) {
        return ResponseEntity.ok(postService.findPostByTitle(title));
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAllPosts() {
        return ResponseEntity.ok(postService.findAllPosts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> editPost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        return ResponseEntity.ok(postService.editPost(id, new Post(postDTO)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePostById(id);
        return ResponseEntity.noContent().build();
    }

}
