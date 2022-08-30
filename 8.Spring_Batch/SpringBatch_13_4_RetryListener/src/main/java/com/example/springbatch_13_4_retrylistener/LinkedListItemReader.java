package com.example.springbatch_13_4_retrylistener;

import org.springframework.aop.support.AopUtils;
import org.springframework.batch.item.ItemReader;

import java.util.LinkedList;
import java.util.List;

/**
 * packageName    : com.example.spring_batch_13_3_skiplistener_retrylistener.skiplistener
 * fileName       : LinkedListItemReader
 * author         : namhyeop
 * date           : 2022/08/26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/26        namhyeop       최초 생성
 */
public class LinkedListItemReader<T> implements ItemReader<T> {

    private List<T> list;

    public LinkedListItemReader(List<T> list) {
        if (AopUtils.isAopProxy(list)) {
            this.list = list;
        }
        else {
            this.list = new LinkedList<>(list);
        }
    }

    @Override
    public T read() throws CustomRetryException {

        if (!list.isEmpty()) {
            T remove = (T)list.remove(0);
            System.out.println("read = " + remove);
            return remove;
        }
        return null;
    }
}
