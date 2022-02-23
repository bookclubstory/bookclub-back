package com.emmett.bookclub.domain.bookclub.member;

import lombok.Getter;

public enum ClubAuth {
    CLUB_OWNER(Constants.OWNER_CODE, "클럽장"),
    SESSION_LEADER(Constants.LEADER_CODE, "세션리더"),
    MEMBER(Constants.MEMBER_CODE, "일반 멤버"),
    BANNED(Constants.BANNED_CODE, "클럽방출");

    @Getter
    final private String code;

    @Getter
    final private String value;

    ClubAuth(String code,String value){
        this.code = code;
        this.value = value;
    }

    public static class Constants  {
        public static final String OWNER_CODE = "CA001";
        public static final String LEADER_CODE = "CA002";
        public static final String MEMBER_CODE = "CA003";
        public static final String BANNED_CODE = "CA004";
    }
}
