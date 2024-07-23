package org.study.swaggertest.batch.member;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MemberJobLauncher extends QuartzJobBean { // Quartz 스케줄러가 이 클래스의 인스턴스를 Job 으로 실행할 수 있도록

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobLauncher jobLauncher = (JobLauncher) context.getMergedJobDataMap().get("jobLauncher"); // Job 실행 런처
        Job printMembersJob = (Job) context.getMergedJobDataMap().get("printMembersJob"); // 실행할 배치 Job 인스턴스

        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters(); // 매번 다른 JobParameters 를 사용해 같은 param 을 가진 Job 재실행 방지
            jobLauncher.run(printMembersJob, jobParameters); // printMembersJob 실행
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
