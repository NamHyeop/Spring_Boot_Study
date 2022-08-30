package com.example.springbatch_13_2_chunklistener_itemreadlistener_itemprocesslist;

import org.springframework.batch.core.ItemProcessListener;

/**
 * packageName    : com.example.springbatch_13_2_chunklistener_itemreadlistener_itemprocesslist
 * fileName       : CustomItemProcessListener
 * author         : namhyeop
 * date           : 2022/08/25
 * description    :
 * ProcessListener 예제
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/25        namhyeop       최초 생성
 */

public class CustomItemProcessListener implements ItemProcessListener<Integer, String> {


    @Override
    public void beforeProcess(Integer item) {
        System.out.println(">> before Process");
    }

    @Override
    public void afterProcess(Integer item, String result) {
        System.out.println(">> after Process");
    }

    @Override
    public void onProcessError(Integer item, Exception e) {
        System.out.println(">> on Process Error");
    }
}
