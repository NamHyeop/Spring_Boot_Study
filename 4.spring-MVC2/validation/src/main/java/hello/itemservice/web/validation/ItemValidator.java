package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Slf4j
@Component
public class ItemValidator implements Validator {

    //supports는 검증기의 유형이 맞는지를 확인하는 함수다. 이 값이 true가 나와야만 정상 동작함.
    //검증기의 종류가 여러개가 될 수 있으므로 존재하는 함수다.
    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
        //item == clazz (검증 대상 클래스와 비교 가능)
        //item == subItm (검증 대상의 자식 클래스 또한 비교 가능)
    }

    //Validator를 상속받고 검증의 논리 과정에 대한 코드를 작성하면 된다.
    //errors안에 BindingResult 들어간다. BindingResult가 errors의 자식이기 때문이다.
    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;

        if(!StringUtils.hasText(item.getItemName())){
            errors.rejectValue("itemName", "required");
        }
        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000){
            //bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, new String[]{"range.item.price"}, new Object[]{1000, 1000000}, null));
            errors.rejectValue("price", "range.item.price", new Object[]{1000, 10000000}, null);
        }
        if(item.getQuantity() == null || item.getQuantity() >= 9999){
            //bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(), false, new String[]{"max.item.quantity"}, new Object[]{9999},  null));
            errors.rejectValue("quantity", "max", new Object[]{9999}, null);
        }
        //특정 필드가 아닌 복합 룰 검증
        if(item.getPrice() != null && item.getQuantity() != null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
                //defaultMessage값에 값을 안주고 codes, argument에만 null값을 주는 것도 가능하다.
                //bindingResult.addError(new ObjectError("item", new String[]{"totalPriceMin"}, new Object[]{10000, resultPrice}, null));
                errors.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }
    }
}
