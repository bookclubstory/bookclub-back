package com.emmett.bookclub.domain.bookpost.like;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostLikeService {
    Flux<Long> updateLike(String postId);
    Flux<Object> deleteLike(String postId);
    Mono<PostLikeDto> getLike(String postId);
}
