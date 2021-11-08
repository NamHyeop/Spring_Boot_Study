package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/validation/v2/items")
@RequiredArgsConstructor
public class ValidationItemControllerV2 {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v2/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v2/addForm";
    }

//    오류가 난 구문이 bindingResult에 담기게된다.
//    매우 중요한 것은 @ModelAttriivute를 사용하고 BindingResult가 매개변수로 선언이 되어야 한다.
//    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        //검증 로직
        //BindingResult에 객체를 포함해야 할 때는 FieldError를 활용해서 포함시켜야한다.
        if(!StringUtils.hasText(item.getItemName())){
            bindingResult.addError(new FieldError("item", "itemName", "상품 이름은 필수입니다."));
        }

        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000){
            bindingResult.addError(new FieldError("item", "price", "가격은 1,000 ~ 1,000,000 까지 허용합니."));
        }

        if(item.getQuantity() == null || item.getQuantity() >= 9999){
            bindingResult.addError(new FieldError("item", "quantity", "수량은 최대 9,999까지 허용합니다."));
        }

        //특정 필드가 아닌 복합 룰 검증
        if(item.getPrice() != null && item.getQuantity() != null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
                bindingResult.addError(new ObjectError("item", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice));
            }
        }

        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            return "validation/v2/addForm";
        }

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    /**
     * v1에서는 중복되는 코드가 많아서 이를 리팩토링함
     * 또한, 오류 발생시 오류 출력과 사용자의 입력값이 오류가 발생하면 사라지는것을 BindingResult를 활용해서 해결함
     */
   // @PostMapping("/add")
    public String addItemV2(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        //검증 로직
        //BindingResult에 객체를 포함해야 할 때는 FieldError를 활용해서 포함시켜야한다.
        if(!StringUtils.hasText(item.getItemName())){
            //3번째 매개변수는 오류가 난 문장을 의미한다. 4번째 매개변수 bindingFailure는 입력데이터값이 제대로 안들어오면 true, 입력 데이터값이 제대로 들어오는데 자료형 오류면 false를 줘야 한다.
            //codes와 argument는 defaultMessage를 대체할 수 있는 매개변수이다.
            bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false, null, null, "상품 이름은 필수입니다."));
        }

        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000){
            bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, null, null,"가격은 1,000 ~ 1,000,000 까지 허용합니."));
        }

        if(item.getQuantity() == null || item.getQuantity() >= 9999){
            bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(), false,null,null, "수량은 최대 9,999까지 허용합니다."));
        }

        //특정 필드가 아닌 복합 룰 검증
        if(item.getPrice() != null && item.getQuantity() != null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
                //defaultMessage값에 값을 안주고 codes, argument에만 null값을 주는 것도 가능하다.
                bindingResult.addError(new ObjectError("item", null, null,"가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice));
            }
        }

        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            return "validation/v2/addForm";
        }

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    /**
     * v2에서 오류 메시지와 오류 값들이 안 사라지고 유지 시키는 구현까지는 성공했지만 오류 문장이 불친절했다
     * errors메시지를 등록하고 이를 활용해서 오류 메시지를 구체적으로 작성시키는 과정을 진행
     */
    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        /**
         * BindingResult는 objectName과 target을 가지고 있음 like 밑에처럼
         * log.info("objectName={]", bindingResult.getObjectName());
         * log.info("target={}", bindingResult.getTarget());
         *
         * 그러므로
         * bindingResult.addError(new FieldError("bindingResult.getObjectName(), itenName, item.getItemName(), false, null, new String[]{"required.item.itemName"},null, null));
         * 도 가능하다.
         */
        //검증 로직
        //BindingResult에 객체를 포함해야 할 때는 FieldError를 활용해서 포함시켜야한다.
        if(!StringUtils.hasText(item.getItemName())){
            //3번째 매개변수는 오류가 난 문장을 의미한다. 4번째 매개변수 bindingFailure는 입력데이터값이 제대로 안들어오면 true, 입력 데이터값이 제대로 들어오는데 자료형 오류면 false를 줘야 한다.
            //codes와 argument는 defaultMessage를 대체할 수 있는 매개변수이다.
            bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false, new String[]{"required.item.itemName"}, null, null));
        }
        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000){
            bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, new String[]{"range.item.price"}, new Object[]{1000, 1000000}, null));
        }
        if(item.getQuantity() == null || item.getQuantity() >= 9999){
            bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(), false, new String[]{"max.item.quantity"}, new Object[]{9999},  null));
        }
        //특정 필드가 아닌 복합 룰 검증
        if(item.getPrice() != null && item.getQuantity() != null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
                //defaultMessage값에 값을 안주고 codes, argument에만 null값을 주는 것도 가능하다.
                bindingResult.addError(new ObjectError("item", new String[]{"totalPriceMin"}, new Object[]{10000, resultPrice}, null));
            }
        }

        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            return "validation/v2/addForm";
        }

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    @PostMapping("/add")
    public String addItemV4(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        /**
         * BindingResult는 objectName과 target을 가지고 있음 like 밑에처럼
         * log.info("objectName={]", bindingResult.getObjectName());
         * log.info("target={}", bindingResult.getTarget());
         *
         * 그러므로
         * bindingResult.addError(new FieldError("bindingResult.getObjectName(), itenName, item.getItemName(), false, null, new String[]{"required.item.itemName"},null, null));
         * 도 가능하다.
         */

        //밑의 검증 로직과 같은 코드. 훨씬 더 간결하 대신 empty나 공백 같은 단순한 기능만 사용이 가능하다.
        ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "itemName", "required");


        //검증 로직
        //BindingResult에 객체를 포함해야 할 때는 FieldError를 활용해서 포함시켜야한다.
//        if(!StringUtils.hasText(item.getItemName())){
//            //3번째 매개변수는 오류가 난 문장을 의미한다. 4번째 매개변수 bindingFailure는 입력데이터값이 제대로 안들어오면 true, 입력 데이터값이 제대로 들어오는데 자료형 오류면 false를 줘야 한다.
//            //codes와 argument는 defaultMessage를 대체할 수 있는 매개변수이다.
//            //bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false, new String[]{"required.item.itemName"}, null, null));
//            bindingResult.rejectValue("itemName", "required");
//        }
        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000){
            //bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, new String[]{"range.item.price"}, new Object[]{1000, 1000000}, null));
            bindingResult.rejectValue("price", "range", new Object[]{1000, 10000000}, null);
        }
        if(item.getQuantity() == null || item.getQuantity() >= 9999){
            //bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(), false, new String[]{"max.item.quantity"}, new Object[]{9999},  null));
            bindingResult.rejectValue("quantity", "max", new Object[]{9999}, null);
        }
        //특정 필드가 아닌 복합 룰 검증
        if(item.getPrice() != null && item.getQuantity() != null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
                //defaultMessage값에 값을 안주고 codes, argument에만 null값을 주는 것도 가능하다.
                //bindingResult.addError(new ObjectError("item", new String[]{"totalPriceMin"}, new Object[]{10000, resultPrice}, null));
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }

        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            return "validation/v2/addForm";
        }

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/validation/v2/items/{itemId}";
    }

}

