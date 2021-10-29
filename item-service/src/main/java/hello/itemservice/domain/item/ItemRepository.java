package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    //실무에서는 동시성 문제를 해결해줄수 잇는 ConcurrentHashMap을 써야한다.
    private static final Map<Long, Item> store = new HashMap<>();
    //Long도 동시성 문제를 해결해줄수있는 atomic long을 사용해야한다.
    private static long sequence = 0L;

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id){
        return store.get(id);
    }

    public List<Item> findAll(){
        //values를 사용하면 map의 값들을 list로 반환한다.
        //그리고 store.values()를 그냥 반환해도 되는데 ArrayList<>를 붙쳐서 값을 반환하는것은 원본값의 변화를 방지하여 안전성의 향상을 위해서이다.
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam){
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice((updateParam.getPrice()));
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore(){
        store.clear();
    }
}
