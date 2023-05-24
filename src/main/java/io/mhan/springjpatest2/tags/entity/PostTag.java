package io.mhan.springjpatest2.tags.entity;

import io.mhan.springjpatest2.posts.entity.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"post", "tag"})
public class PostTag {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    private Post post;

    @ManyToOne(fetch = LAZY)
    private Tag tag;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static PostTag create(Post post, Tag tag) {
        PostTag postTag = PostTag.builder()
                .post(post)
                .tag(tag)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return postTag;
    }
}
