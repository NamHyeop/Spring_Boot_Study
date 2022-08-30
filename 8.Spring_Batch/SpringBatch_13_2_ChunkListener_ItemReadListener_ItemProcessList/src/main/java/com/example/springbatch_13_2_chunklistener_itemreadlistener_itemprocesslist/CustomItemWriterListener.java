package com.example.springbatch_13_2_chunklistener_itemreadlistener_itemprocesslist;

import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

/**
 * packageName    : com.example.springbatch_13_2_chunklistener_itemreadlistener_itemprocesslist
 * fileName       : CustomItemWriterListener
 * author         : namhyeop
 * date           : 2022/08/25
 * description    :
 * WriterListener 예제
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/25        namhyeop       최초 생성
 */
public class CustomItemWriterListener implements ItemWriteListener<String> {

    @Override
    public void beforeWrite(List<? extends String> items) {
        System.out.println(">> before write");
    }

    @Override
    public void afterWrite(List<? extends String> items) {
        System.out.println(">> after write");
    }

    @Override
    public void onWriteError(Exception exception, List<? extends String> items) {
        System.out.println(">> on write Error");
    }
}
