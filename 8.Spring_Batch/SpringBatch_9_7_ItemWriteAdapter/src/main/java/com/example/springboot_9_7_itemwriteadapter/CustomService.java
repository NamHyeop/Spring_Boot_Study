package com.example.springboot_9_7_itemwriteadapter;

import org.springframework.stereotype.Service;

/**
 * packageName    : com.example.springboot_9_7_itemwriteadapter
 * fileName       : CustomService
 * author         : namhyeop
 * date           : 2022/08/15
 * description    :
 * write 실행 도중 실행될 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/15        namhyeop       최초 생성
 */

public class CustomService<T> {

    public void customWrite(T item){
        System.out.println("item = " + item);
    }
}
