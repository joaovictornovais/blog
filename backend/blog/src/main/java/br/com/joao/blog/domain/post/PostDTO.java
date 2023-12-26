package br.com.joao.blog.domain.post;

import java.time.LocalDateTime;

public record PostDTO(String title, String subtitle, LocalDateTime date, String content) {
}
