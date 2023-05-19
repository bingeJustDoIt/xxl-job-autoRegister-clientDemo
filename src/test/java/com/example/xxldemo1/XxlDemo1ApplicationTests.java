package com.example.xxldemo1;

import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.support.CronSequenceGenerator;

import java.util.Date;

//@SpringBootTest
class XxlDemo1ApplicationTests {

    @Test
    void contextLoads() {
        String cronExp="0 0 3 */4 * *";
        // 解析cron表达式
        final CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(cronExp);
        // 任务下次执行时间

        // 任务下下次执行时间
        Date nextTime=new Date();
        for (int i = 0; i < 30; i++) {
            nextTime = cronSequenceGenerator.next(nextTime);
            System.out.println(DateUtil.formatDateTime(nextTime));
        }
    }

}
