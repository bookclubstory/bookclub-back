package com.emmett.bookclub.core.schedule;

import org.springframework.stereotype.Component;

@Component
public class ScheduleTest {
    private final ScheduleService scheduleService;

    public ScheduleTest(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

}
