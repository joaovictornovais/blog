package br.com.joao.blog.domain.post;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_post")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String subtitle;
    private LocalDateTime date;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    public Post(PostDTO postDTO) {
        BeanUtils.copyProperties(postDTO, this);
    }


}
