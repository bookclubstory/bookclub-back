package com.emmett.bookclub.domain.system.code;

import lombok.Getter;

public enum UpperCode {
    CLUB_AUTH("CLUB_AUTH", "CLUB_AUTH");

    @Getter
    final private String code;

    @Getter
    final private String value;

    UpperCode(String code, String value){
        this.code = code;
        this.value = value;
    }
}
