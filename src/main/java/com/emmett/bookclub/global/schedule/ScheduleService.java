package com.emmett.bookclub.global.schedule;

import java.io.IOException;

public interface ScheduleService {

    void runNoticeMailing();

    void getBeforeSpecification() throws IOException;
    void getBidNotice() throws IOException;
}
