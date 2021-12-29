package com.emmett.bookclub.core.schedule;

import java.io.IOException;

public interface ScheduleService {

    void runNoticeMailing();

    void getBeforeSpecification() throws IOException;
    void getBidNotice() throws IOException;
}
