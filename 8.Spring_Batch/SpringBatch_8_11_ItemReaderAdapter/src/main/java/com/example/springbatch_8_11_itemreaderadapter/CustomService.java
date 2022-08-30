package com.example.springbatch_8_11_itemreaderadapter;

/**
 * packageName    : com.example.springbatch_8_10_itemreaderadapter
 * fileName       : CustomService
 * author         : namhyeop
 * date           : 2022/08/13
 * description    :
 * 예제에서 종료 메소드를 설정 안해줬기 때문에 무한으로 cnt가 증가한다.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/13        namhyeop       최초 생성
 */
public class CustomService<T> {
    private int cnt = 0;
    public T joinCustomer(){
        return(T)("item" + cnt++);
    }
}
