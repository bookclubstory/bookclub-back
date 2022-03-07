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

    @GetMapping("/{postId}")
    public ResponseEntity<Mono<PostLikeDto>> getLike(@PathVariable String postId){
        Mono<PostLikeDto> likeDto = postLikeService.getLike(postId);
        return new ResponseEntity<>(likeDto, HttpStatus.OK);
    }

    @PostMapping("/{postId}")
    public ResponseEntity<Flux<Long>> updateLike(@PathVariable String postId){
        Flux<Long> result = postLikeService.updateLike(postId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Flux<Object>> deleteLike(@PathVariable String postId){
        Flux<Object> result = postLikeService.deleteLike(postId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
