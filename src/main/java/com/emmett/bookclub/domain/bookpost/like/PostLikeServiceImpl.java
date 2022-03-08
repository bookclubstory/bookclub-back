package com.emmett.bookclub.domain.bookpost.like;

import com.emmett.bookclub.domain.model.exception.BadRequestException;
import com.emmett.bookclub.global.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveSetOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class PostLikeServiceImpl implements PostLikeService{
    private final ReactiveRedisOperations<String, String> redisOperations;
    private final Util util;

    @Override
    public Flux<Long> updateLike(String postId) {
        String loginId = util.getLoginId();
        ReactiveSetOperations<String, String> operations = redisOperations.opsForSet();

        return operations.members(postId)
                .filter(member->loginId.equals(member))
                .flatMap(s->operations.remove(postId,s))
                .switchIfEmpty(Mono.defer(()->operations.add(postId, loginId)));
    }

    @Override
    public Flux<Object> deleteLike(String postId) {
        String loginId = util.getLoginId();
        ReactiveSetOperations<String, String> operations = redisOperations.opsForSet();

        return operations.members(postId)
                .filter(member->loginId.equals(member))
                .switchIfEmpty(Mono.error(new BadRequestException("이미 취소 되었습니다.")))
                .flatMap(s -> operations.remove(postId,s));
    }

    @Override
    public Mono<PostLikeDto> getLike(String postId) {
        String loginId = util.getLoginId();

        return redisOperations.opsForSet().members(postId)
                .collectList()
                .map(list -> new PostLikeDto(postId, loginId, (long)list.size(), list.contains(loginId)));
    }
}

