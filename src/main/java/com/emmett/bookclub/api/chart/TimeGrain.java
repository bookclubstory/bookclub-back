package com.emmett.bookclub.api.chart;

import com.emmett.bookclub.core.util.Pair;
import lombok.Getter;

public enum TimeGrain {
    SECOND("SECOND", "second"),
    MINUTE("MINUTE", "minute"),
    HOUR("HOUR", "hour"),
    DAY("DAY", "day"),
    WEEK("WEEK", "week"),
    MONTH("MONTH", "month"),
    QUARTER("QUARTER", "quarter"),
    YEAR("YEAR", "year");

    @Getter
    final private String code;
    @Getter
    final private String value;

    TimeGrain(String code, String value) {
        this.code = code;
        this.value = value;
    }

    Pair<String, String> getCodeAndValue() { return new Pair<>(code, value); }
}
