package com.emmett.bookclub.domain.bookpost.like;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostLikeService {
    Flux<Object> pushLike(String boardId);
    Flux<Object> cancelLike(String boardId);
    Mono<PostLikeDto> getLike(String boardId);
}
