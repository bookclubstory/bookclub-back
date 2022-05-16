package com.emmett.bookclub.domain.bookpost.like;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostLikeDto implements Serializable {
    private String postId;
    private String loginId;
    private Long likeCnt;
    private boolean isSelected;
}
