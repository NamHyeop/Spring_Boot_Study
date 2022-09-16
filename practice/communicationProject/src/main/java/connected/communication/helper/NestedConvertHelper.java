package connected.communication.helper;

import connected.communication.exception.CannotConvertNestedStructureException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

/**
 * packageName    : connected.communication.helper
 * fileName       : NestedConverHelper
 * author         : namhyeop
 * date           : 2022/09/05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/05        namhyeop       최초 생성
 */
public class NestedConvertHelper<K,E,D> {

    private List<E> entities;
    private Function<E,D> toDto;
    private Function<E,E> getParent;
    private Function<E,K> getKey;
    private Function<D,List<D>> getChildren;

    public static <K,E,D> NestedConvertHelper newInstance(List<E> entities, Function<E,D> toDto, Function<E,E> getParent, Function<E,K> getKey, Function<D, List<D>> getChildren){
        return new NestedConvertHelper<K,E,D>(entities, toDto, getParent, getKey, getChildren);
    }

    public NestedConvertHelper(List<E> entities, Function<E, D> toDto, Function<E, E> getParent, Function<E, K> getKey, Function<D, List<D>> getChildren) {
        this.entities = entities;
        this.toDto = toDto;
        this.getParent = getParent;
        this.getKey = getKey;
        this.getChildren = getChildren;
    }

    public List<D> convert(){
        try{
            return convertInternal();
        }catch (NullPointerException e){
            throw new CannotConvertNestedStructureException(e.getMessage());
        }
    }

    private List<D> convertInternal(){
        HashMap<K, D> map = new HashMap<>();
        ArrayList<D> roots = new ArrayList<>(); //자식 없는 root Entity가 담기게 된다.

        for(E e : entities){ //카테고리 전체를 탐색한 entities를 순차적으로 탐색
            D dto = toDto(e);
            map.put(getKey(e), dto);//탐색된 entity를 DTO로 변환하여 map 넣어준다. 이미 탐색된 부모 엔티티의 DTO는, 어떤 자식 엔티티를 탐색할 때 반드시 Map에 담겨있어야합니다. 그렇지 않다면 NullPointerException 예외가 발생하여 변환 작업이 실패하는 것입니다.
            if(hasParent(e)){ //부모가 있따면 Map에서 부모의 Dto를 찾아준다. entities는 정렬된 상태이기에 부모 엔터티의 DTO는 Map에 이미 있을것이다.없을 경우에는 루트 엔터티이다.
                E parent = getParent(e);
                K parentKey = getKey(parent);
                D parentDto = map.get(parentKey);
                getChildren(parentDto).add(dto);
            }else{
                roots.add(dto);
            }
        }
        return roots;
    }

    private boolean hasParent(E e){
        return getParent(e) != null;
    }

    private E getParent(E e){
        return getParent.apply(e);
    }

    private D toDto(E e){
        return toDto.apply(e);
    }

    private K getKey(E e){
        return getKey.apply(e);
    }

    private List<D> getChildren(D d){
        return getChildren.apply(d);
    }
}
