package com.example.spring_batch_13_3_skiplistener_retrylistener.retrylistenr;

import org.springframework.aop.support.AopUtils;
import org.springframework.batch.item.ItemReader;
import org.springframework.lang.Nullable;

import java.util.LinkedList;
import java.util.List;

/**
 * packageName    : com.example.spring_batch_13_3_skiplistener_retrylistener
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

    public LinkedListItemReader(List<T> list){
        if (AopUtils.isAopProxy(list)){
            this.list = list;
        }
        else{
            this.list = new LinkedList<>(list);
        }
    }

    @Nullable
    @Override
    public T read() throws CustomSkipException{

        if(!list.isEmpty()){
            T remove = (T)list.remove(0);
            if ((Integer)remove == 3){
                throw new CustomSkipException("read skipped : " + remove);
            }
            System.out.println("read = " + remove);
            return remove;
        }
        return null;
    }
}
