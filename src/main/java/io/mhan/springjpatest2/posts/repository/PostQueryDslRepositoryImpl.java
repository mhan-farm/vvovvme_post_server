package io.mhan.springjpatest2.posts.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.mhan.springjpatest2.posts.entity.Post;
import io.mhan.springjpatest2.posts.repository.vo.Keyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.mhan.springjpatest2.posts.entity.QPost.post;

@Repository
@RequiredArgsConstructor
public class PostQueryDslRepositoryImpl implements PostQueryDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> findAll(Keyword keyword) {

        // 쿼리 생성
        JPAQuery<Post> contentQuery = jpaQueryFactory
                .select(post)
                .from(post)
                .where(containsKeyword(keyword));

        // 쿼리 실행
        List<Post> posts = contentQuery.fetch();

        return posts;
    }

    private BooleanExpression containsKeyword(Keyword keyword) {
        return keyword == null ? null : switch (keyword.getType()) {
            case TITLE -> post.title.contains(keyword.getValue());
            case TITLE_CONTENT -> null;
        };
    }
}
