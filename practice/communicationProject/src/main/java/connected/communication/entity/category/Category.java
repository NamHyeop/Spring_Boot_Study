package connected.communication.entity.category;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * packageName    : connected.communication.entity.category
 * fileName       : Category
 * author         : namhyeop
 * date           : 2022/09/04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/04        namhyeop       최초 생성
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    //삭제시 onDelete방식을 사용해서 DB 진영에서 Alter 형식으로 쿼리가 삭제되도록 수행하기 위해 아래와 같은 방식을 사용안했음
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Category parent;

    public Category(String name, Category parent){
        this.name = name;
        this.parent = parent;
    }
}
