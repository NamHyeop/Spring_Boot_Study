//package com.example.springbatch.springbatchlecture;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@RequiredArgsConstructor
//@Configuration
//public class HelloJobConfiguration {
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job helloJob(){
//        return this.jobBuilderFactory.get("step2.job")
//                .start(helloStep1())
//                .next(helloStep2())
//                .build();
//    }
//
//    @Bean
//    public Step helloStep1(){
//        return stepBuilderFactory.get("helloStep1")
//                /**
//                 * 이론에서 말했던 Tasklet 영역이다.
//*                * 기본값 으로는 무한 반복이 기본값이다.
//                 */
//                .tasklet(new Tasklet() {
//                    /**
//                     * RepeatStatus가
//                     * 객체의 이름이 RepeatStatus것 만큼 이 안에서 동작할 step이 반복적으로 동작할 지 아니면 한 번만 동작할 지를 결정한다.
//                     * execute의 반환값이 null이라면 한 번 실행되고 정지된다.
//                     * RepeatStatus와 같은 의미를 가지는것이 RepeatStatus.FINISHED이다.
//                     * 반복적으로 값을 동작시키고 싶다면 RepeatStatus.CONTINUABLE로 설정하면 된다.
//                     */
//                    @Override
//                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("=================================");
//                        System.out.println(" >> Step1 has executed");
////                        System.out.println(" >> 실시간 상품 조회중");
//                        System.out.println("=================================");
//                        return RepeatStatus.CONTINUABLE;
//                    }
//                }).build();
//    }
//
//    @Bean
//    public Step helloStep2(){
//        return stepBuilderFactory.get("helloStep2")
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("=================================");
//                    System.out.println(" >> Step2 has executed");
//                    System.out.println("=================================");
//                    return RepeatStatus.FINISHED;
//                }).build();
//    }
//}
