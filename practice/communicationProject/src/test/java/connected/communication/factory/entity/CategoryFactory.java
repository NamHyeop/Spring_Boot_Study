package connected.communication.factory.entity;

import connected.communication.entity.category.Category;

/**
 * packageName    : connected.communication.entity.category
 * fileName       : CategoryFactory
 * author         : namhyeop
 * date           : 2022/09/05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/05        namhyeop       최초 생성
 */
public class CategoryFactory {

    public static Category createCategory(){
        return new Category("name", null);
    }

    public static Category createCategory(String name, Category parent){
        return new Category(name, parent);
    }

    public static Category createCategoryWithName(String name){
        return new Category(name, null);
    }
}
