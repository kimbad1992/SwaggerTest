package org.study.swaggertest.batch.member;

import org.quartz.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
public class MemberQuartzConfig {

    // 스케줄러 팩토리 빈 설정
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(Trigger trigger, JobDetail jobDetail) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setJobDetails(jobDetail); // Job 의 세부 정보를 설정
        schedulerFactory.setTriggers(trigger); // Job 이 언제 실행될지를 결정
        return schedulerFactory;
    }

    // JobDetail 설정
    @Bean
    public JobDetailFactoryBean jobDetail(JobLauncher jobLauncher, Job printMembersJob) {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(MemberJobLauncher.class); // Quartz 에서 실행할 Job 의 클래스 설정
        jobDetailFactoryBean.setDescription("Print Members Job"); // Job 설명
        jobDetailFactoryBean.setDurability(true); // Job 영구 보관 (Job 실행 완료 후에도 스케쥴러에서 삭제 X)
        jobDetailFactoryBean.getJobDataMap().put("jobLauncher", jobLauncher); // Job 실행 시 필요한 데이터 저장
        jobDetailFactoryBean.getJobDataMap().put("printMembersJob", printMembersJob); // Job 실행 시 필요한 데이터 저장
        return jobDetailFactoryBean;
    }

    // Simple 트리거 설정
    @Bean
    public SimpleTriggerFactoryBean trigger(JobDetail jobDetail) {
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(jobDetail);
        trigger.setRepeatInterval(30000); // Job 실행 간격 - m/s (3000: 30초마다)
        trigger.setStartDelay(0); // 트리거가 최초 시작되기까지의 지연 시간 - m/s (0: 즉시 시작)
        return trigger;
    }

    // Cron 트리거 설정
//    @Bean
//    public CronTriggerFactoryBean cronTrigger(JobDetail jobDetail) {
//        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
//        trigger.setJobDetail(jobDetail);
//        trigger.setCronExpression("*/30 * * * *");
//        trigger.setStartDelay(0);
//        return trigger;
//    }
}
