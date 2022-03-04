package com.emmett.bookclub.domain.bookpost.like;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookpost/like")
public class PostLikeController {
    private final PostLikeService postLikeService;

    @GetMapping("/{boardId}")
    public ResponseEntity<Mono<PostLikeDto>> getLike(@PathVariable String boardId){
        Mono<PostLikeDto> likeDto = postLikeService.getLike(boardId);
        return new ResponseEntity<>(likeDto, HttpStatus.OK);
    }

    @PostMapping("/{boardId}")
    public ResponseEntity<Flux<Object>> updateLike(@PathVariable String boardId){
        Flux<Object> result = postLikeService.pushLike(boardId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Flux<Object>> deleteLike(@PathVariable String boardId){
        Flux<Object> result = postLikeService.cancelLike(boardId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
