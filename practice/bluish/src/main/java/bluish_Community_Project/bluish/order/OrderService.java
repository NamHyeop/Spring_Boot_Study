package bluish_Community_Project.bluish.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
