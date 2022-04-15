package hello.itemservice.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class DeliveryCode {
    private String code; //FAST 같은 시스템에서 전달하는 값
    private String displayName; //빠른배송 같은 고객에게 보여주기 위한 값
}

/**
 * AllArgsConstructor는 클래스의 모든 필드의 생성자를 자동 주입해준다.
 * @NonNull이 체크되어있다면 생성장 내에 NullCheck 로직을 자동적으로 생성한다.
 * java 코드로 구현하면 밑에와 같은 역할을 수행한다.
 * */

//public class DeliveryCode(@NonNull String code, String disPlayName){
//    if(code == null){
//        throw new NullPointerException("code is null");
//    }
//    this.code = code;
//    this.dsPlayName = disPlayName;
//}