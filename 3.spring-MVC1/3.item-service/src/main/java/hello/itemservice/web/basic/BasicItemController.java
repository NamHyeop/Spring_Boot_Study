package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
//@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;

    //지금처럼 생성자가 하나만 있는 경우 @RequiredArgsContructor Annotaion을 사용할 경우 생성자를 자동으로 만들어서 넣어준다.
    //생성자가 하나만 있는 경우 @Autowirede가 생략 가능하다.
//    public BasicItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    //http로 get 메소드가 들어올 경우 응답하는 경우
    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    //get이오면 이걸
    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    /**
     * 여기부터 save 기능들을 점점 간편하게 작성하는 방법들에 대한 코드가 나열되어있다.
     */
    //post가 오면 이걸 작동. HTML의 addform의 label 밑의 정보중에 name의 이름으로 RequestParam을 설정해줘야한다.
    //@PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);
        model.addAttribute("item", item);
        return "basic/item";
    }

    //addItemV1과 비교해서 코드를 보자
    //attriuteName은 html이 아니라 여기서정하는것이다. 헷갈리지말자
   //@PostMapping("/add")
    public String addItemV2(@ModelAttribute("whywhywhy") Item item, Model model){

        /*자동 추가 생략가능
        Item item = new Item();
        item.setItemName(items.getItemName());
        item.setPrice(items.getPrice());
        item.setQuantity(items.getQuantity());*/
        itemRepository.save(item);
        //이걸 주석 처리하면 model.addAttribute("whywhywhy",item)이 들어가기 때문에 등록화면이 정상적으로 화면이 안나온다..
        //model.addAttribute("item", item); //자동 추가, 생략가능
        return "basic/item";
    }

    //ModelAttribute의 속성 이름을 지우면 바로 옆의 클래스 이름의 첫 글자를 소문자로 바꾼 이름이 속성 이름으로 지정된다
    //예시의 경우 Item -> item으로 이름이 지정되게 된다.
    //model도 생략가능 밖에서 다만들어준다.

    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item){
        itemRepository.save(item);
        return "basic/item";
    }

    //현재는 Item이라는 사용자가 만든 객체라 @ModelAttribute를 지울수도 있다.
    //@PostMapping("/add")
    public String addItemV4(Item item){
        itemRepository.save(item);
        //새로고침 문제를 해결하기 위한 PRG 적용 기법
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV5(Item item){
        itemRepository.save(item);
        //새로고침 문제를 해결하기 위한 PRG 적용 기법
        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes){
        Item savedItem = itemRepository.save(item);
        //여기의 savedItem.getId가 return의 itemId로 치환된다.
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        //return에 안적힌 애들의 query 파리미터로 넘온다. 현재 status가 예시
        redirectAttributes.addAttribute("status", true);
        //새로고침 문제를 해결하기 위한 PRG 적용 기법
        return "redirect:/basic/items/{itemId}";
    }
    /**
     * 여기부터 상품 수정 코드
     */

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        log.info("process test");
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId, item);
        //redirect를 활용한 경로설정
        return "redirect:/basic/items/{itemId}";
    }
    /**
     *  테스트용 데이터
     */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

//    @ResponseBody
//    @PostMapping("/orderpro123")
//    public void test(@RequestBody TestDto testDto){
//        System.out.println("testDto = " + testDto);
//        List<Long> numbers = testDto.getNumbers();
//        for (Long number : numbers) {
//            System.out.println("number = " + number);
//        }
//    }

    @ResponseBody
    @GetMapping("/orderpro124")
    public void test2(@RequestBody Map<String, List<String>> testDto){
        System.out.println("testDto = " + testDto);
        List<String> id = testDto.get("id");
        List<String> password = testDto.get("password");
        List<String> strings = testDto.get("email");
        for (String string : strings) {
            System.out.println("string = " + string);
        }
//        List<String> numbers = testDto.getNumbers();
//        for (Object o : numbers) {
//            System.out.println("o = " + o);
//        }
    }

    @ResponseBody
    @GetMapping("/orderpro125")
    public void test2(@RequestParam TestDto testDto){
        System.out.println("testDto = " + testDto);
    }

    @Data
    static class TestDto implements Serializable {
        String numbers;
    }

    @Data
    static class TestDto2{
        String abc;
    }
}
