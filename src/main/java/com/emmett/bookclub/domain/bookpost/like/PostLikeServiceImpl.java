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
    public Flux<Object> pushLike(String boardId) {
        String loginId = util.getLoginId();
        ReactiveSetOperations<String, String> operations = redisOperations.opsForSet();

        return operations.members(boardId)
                .filter(member->loginId.equals(member))
                .flatMap(s -> Mono.error(new BadRequestException(loginId + "는 해당 게시물에 이미 좋아요를 눌렀습니다.")))
                .switchIfEmpty(operations.add(boardId,loginId));
    }

    @Override
    public Flux<Object> cancelLike(String boardId) {
        String loginId = util.getLoginId();
        ReactiveSetOperations<String, String> operations = redisOperations.opsForSet();

        return operations.members(boardId)
                .filter(member->loginId.equals(member))
                .switchIfEmpty(Mono.error(new BadRequestException("이미 취소 되었습니다.")))
                .flatMap(s -> operations.remove(boardId,s));
    }

    @Override
    public Mono<PostLikeDto> getLike(String boardId) {
        String loginId = util.getLoginId();

        return redisOperations.opsForSet().members(boardId)
                .collectList()
                .map(list -> new PostLikeDto(boardId, loginId, (long)list.size(), list.contains(loginId)));
    }
}

