package com.emmett.bookclub.api.data.openapi;

import com.emmett.bookclub.core.schedule.ScheduleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class OpenapiServiceTest {
    @Autowired
    OpenapiService openapiService;
    @Autowired
    ScheduleService scheduleService;

    @Test
    public void getOpenapiBeforeSpec() throws IOException {
        //scheduleService.getBeforeSpecification();

        String dateFrom = "202112010000";
        String dateTo = "202112212359";
        openapiService.callApiForBeforeSpecification(dateFrom, dateTo);
    }

    @Test
    public void getOpenapiBidNotice() throws IOException {
        //scheduleService.getBidNotice();

        String dateFrom = "202112090000";
        String dateTo = "202112092359";
        openapiService.callApiForBidNotice(dateFrom, dateTo);
    }
}
