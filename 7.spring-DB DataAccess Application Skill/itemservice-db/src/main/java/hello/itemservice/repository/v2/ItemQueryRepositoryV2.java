package hello.itemservice.repository.v2;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemSearchCond;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static hello.itemservice.domain.QItem.item;

/**
 * QueryDsl을 활용한 동적쿼리 등록 또는 조회를 위한 Repository
 */
@Repository
public class ItemQueryRepositoryV2 {
    private final JPAQueryFactory query;

    public ItemQueryRepositoryV2(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public List<Item> findAll(ItemSearchCond cond){
        return query.select(item)
                .from(item)
                .where(
                        maxPrice(cond.getMaxPrice()),
                        likeItemName(cond.getItemName()))
                .fetch();
    }

    private BooleanExpression maxPrice(Integer maxPrice) {
        if(maxPrice != null){
            return item.price.loe(maxPrice);
        }
        else{
            return null;
        }
    }

    private BooleanExpression likeItemName(String itemName){
        if(StringUtils.hasText(itemName)){
           return item.itemName.like("%" + itemName + "%");
        }
        else{
            return null;
        }
    }
}
