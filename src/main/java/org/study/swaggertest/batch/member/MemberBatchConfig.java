package org.study.swaggertest.batch.member;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.listener.ChunkListenerSupport;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.study.swaggertest.dto.Member;

import java.util.Arrays;
import java.util.List;

@Configuration
public class MemberBatchConfig {

    @Autowired
    private JobRepository jobRepository; // Spring Batch 의 핵심 구성 요소로, Job 실행 상태 등을 관리

    @Autowired
    private PlatformTransactionManager transactionManager; // 데이터베이스 트랜잭션을 관리

    @Bean
    public Job printMembersJob() {
        JobBuilder jobBuilder = new JobBuilder("printMembersJob", jobRepository);

        return jobBuilder
                .start(printMembersStep()) // Job이 실행할 첫 번째 Step을 설정합니다
                .build();
    }

    @Bean
    public Step printMembersStep() {
        StepBuilder stepBuilder = new StepBuilder("printMembersStep", jobRepository);

        return stepBuilder
                .<Member, Member>chunk(3, transactionManager) // 3개의 레코드를 하나의 트랜잭션으로 묶어 처리
                .reader(memberReader()) // 데이터를 읽는 Reader
                .processor(memberProcessor()) // 데이터 로직 처리 및 가공 등
                .listener(new ChunkListenerSupport() {
                    @Override
                    public void afterChunk(ChunkContext context) {
                        System.out.println("청크가 커밋되었습니다.");
                    }
                })
                .writer(memberWriter()) // 처리된 데이터를 출력하거나 다른 저장소에 저장 등의 로직
                .build();
    }

    @StepScope
    @Bean
    public ItemReader<Member> memberReader() {
        // DB 등 데이터를 읽어온다...

        List<Member> members = Arrays.asList(
                new Member(1L, "Member1"),
                new Member(2L, "Member2"),
                new Member(3L, "Member3"),
                new Member(4L, "Member4"),
                new Member(5L, "Member5"),
                new Member(6L, "Member6"),
                new Member(7L, "Member7"),
                new Member(8L, "Member8"),
                new Member(9L, "Member9"),
                new Member(10L, "Member10")
        );
        return new ListItemReader<>(members);
    }

    @Bean
    public ItemProcessor<Member, Member> memberProcessor() {
        // 데이터 로직 처리 및 가공 등...
        return member -> member;
    }

    @Bean
    public ItemWriter<Member> memberWriter() {
        return items -> {
            System.out.println("현재 청크 내용:");
            items.forEach(member -> System.out.println(member.getName()));

            // DB 적재 등...
        };
    }
}

