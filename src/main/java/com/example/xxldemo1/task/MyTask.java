package com.example.xxldemo1.task;

import com.example.xxljobcustom.annotation.XxlRegister;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyTask {
    @XxlJob(value = "testJob")
    //自动注册到xxl-job
    @XxlRegister(cron = "0/3 * * * * ?",
            author = "binge",
            jobDesc = "测试job")
    public void testJob() {
        log.info("hello world");
    }
}
