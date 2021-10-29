package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//Data 사용은 위험하다. ToString, EqualsAndHashCode 등 너무 많은 기능을 지원하기 때문이다. 그러므로 Getter 또는 Setter 까지만 사용하자
@Getter @Setter
public class Item {
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
