package com.example.springbatch_11_4_retry_1.template;

import com.example.springbatch_11_4_retry_1.RetryableException;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.classify.BinaryExceptionClassifier;
import org.springframework.classify.Classifier;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.DefaultRetryState;
import org.springframework.retry.support.RetryTemplate;

/**
 * packageName    : com.example.springbatch_11_4_retry_1
 * fileName       : RetryItemProcesssor
 * author         : namhyeop
 * date           : 2022/08/16
 * description    :
 * Batch 실행중 실행될 프로세스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/16        namhyeop       최초 생성
 */
public class RetryItemProcessor2 implements ItemProcessor<String, Customer> {

    @Autowired private RetryTemplate retryTemplate;

    private int cnt;

    @Override
    public Customer process(String item) throws Exception {
        //재시도시 무조건 시도되게 반환값을 True로 설정
        Classifier<Throwable, Boolean> rollbackClassifier = new BinaryExceptionClassifier(true);

        Customer customer = retryTemplate.execute(
                new RetryCallback<Customer, RuntimeException>() {
                    @Override
                    public Customer doWithRetry(RetryContext retryContext) throws RuntimeException {
                        if(item.equals("1") || item.equals("2")){
                            cnt++;
                            throw new RetryableException("failed cnt : " + cnt);
                        }
                        return new Customer(item);
                    }
                },
                new RecoveryCallback<Customer>() {
                    @Override
                    public Customer recover(RetryContext retryContext) throws Exception {
                        return new Customer(item);
                    }
                }, new DefaultRetryState(item, rollbackClassifier));
        return customer;
    }
}
