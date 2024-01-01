package br.com.joao.blog.services;

import br.com.joao.blog.domain.post.Post;
import br.com.joao.blog.repositories.PostRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    private final PostRespository postRespository;

    public PostService(PostRespository postRespository) {
        this.postRespository = postRespository;
    }

    public Post createPost(Post post) {
        post.setDate(LocalDateTime.now());
        return postRespository.save(post);
    }

    public Post findPostById(Long id) {
        return postRespository.findById(id).orElseThrow(() -> new RuntimeException("Post not found with ID " + id));
    }

    public List<Post> findAllPosts() {
        return postRespository.findAll();
    }

    public List<Post> findPostByTitle(String title) {
        return postRespository.findPostsByTitleContainingIgnoreCase(title);
    }

    public Post editPost(Long id, Post newPost) {
        Post post = findPostById(id);
        BeanUtils.copyProperties(newPost, post);
        post.setDate(LocalDateTime.now());
        post.setId(id);
        return postRespository.save(post);
    }

    public void deletePostById(Long id) {
        postRespository.deleteById(id);
    }
}
