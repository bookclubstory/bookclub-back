package com.emmett.bookclub.domain.bookpost.like;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostLikeDto {
    private String boardId;
    private String loginId;
    private Long likeCnt;
    private boolean isSelected;
}
