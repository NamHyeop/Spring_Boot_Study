package bluish_Community_Project.bluish.singleontest;

import org.springframework.stereotype.Component;

public class StatefulService {
    private int price;

    public void order(String name, int Value){
        System.out.println("name = " + name + " value = " + Value);
        this.price = Value; //state한 상태가 아닌 상태로 만드는 부분
    }
    public int getPrice() {
        return price;
    }
}
