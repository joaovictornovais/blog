package br.com.joao.blog.repositories;

import br.com.joao.blog.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRespository extends JpaRepository<Post, Long> {

    List<Post> findPostsByTitleContainingIgnoreCase(String title);

}