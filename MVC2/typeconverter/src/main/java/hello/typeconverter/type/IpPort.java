package hello.typeconverter.type;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
//객체끼리 비교니까 따로 정의를 해야하는데 @EqualsAndHashCode 사용하면 자동으로 생성됨
//C++ 사용할 때는 boolean으로 구현해줬는데... 혁명이다
@EqualsAndHashCode
public class IpPort {
    private String ip;
    private int port;

    public IpPort(String ip, int port){
        this.ip = ip;
        this.port = port;
    }
}
