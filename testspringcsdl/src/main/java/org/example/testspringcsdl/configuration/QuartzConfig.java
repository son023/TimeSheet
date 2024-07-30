//package org.example.testspringcsdl.configuration;
//
//import org.quartz.CronScheduleBuilder;
//import org.quartz.JobBuilder;
//import org.quartz.JobDetail;
//import org.quartz.Trigger;
//import org.quartz.TriggerBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class QuartzConfig {
//
//    @Bean
//    public JobDetail emailJobDetail() {
//        return JobBuilder.newJob(EmailJob.class)
//                .withIdentity("emailJob")
//                .storeDurably()
//                .build();
//    }
//
//    @Bean
//    public Trigger emailTrigger() {
//        return TriggerBuilder.newTrigger()
//                .forJob(emailJobDetail())
//                .withIdentity("emailTrigger")
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 17 * * ?")) // Gửi email vào lúc 17:00 mỗi ngày
//                .build();
//    }
//}