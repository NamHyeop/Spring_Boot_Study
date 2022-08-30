package com.example.springbatch_13_2_chunklistener_itemreadlistener_itemprocesslist;


import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.example.springbatch_13_2_chunklistener_itemreadlistener_itemprocesslist
 * fileName       : CustomItemReadListener
 * author         : namhyeop
 * date           : 2022/08/25
 * description    :
 * ItemReadListener 예제
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/25        namhyeop       최초 생성
 */

@Component
public class CustomItemReadListener implements ItemReadListener {

    @Override
    public void beforeRead() {
        System.out.println(">> before Read");
    }

    @Override
    public void afterRead(Object o) {
        System.out.println(">> after Read");
    }

    @Override
    public void onReadError(Exception e) {
        System.out.println(">> on Read Error");
    }
}
