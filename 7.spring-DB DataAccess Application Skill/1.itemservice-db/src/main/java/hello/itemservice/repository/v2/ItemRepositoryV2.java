package hello.itemservice.repository.v2;

import hello.itemservice.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Sprint Data JPA를 활용한 기본 쿼리 조회 Repository
 */
public interface ItemRepositoryV2 extends JpaRepository<Item,Long> {
}
