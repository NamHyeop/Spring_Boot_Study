package hello.typeconverter.controller;

import lombok.Data;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class FormatterController {

    @GetMapping("/formatter/edit")
    public String formatterForm(Model model){
        Form form = new Form();
        form.setNumber(10000);
        form.setLocalDateTime(LocalDateTime.now());

        model.addAttribute("form", form);
        return "formatter-form";
    }

    @PostMapping("/formatter/edit")
    //앞에서도 배웠지만 @ModelAttrivute로 이렇게 지정해주면 Model.addAttribute()이짓거리 안해도 자동 추가해줌
    public String formatterEdit(@ModelAttribute Form form){
        return "formatter-view";
    }

    //아래에서 설정한 어노테이션 형식대로 데이터가 저장된다.
    @Data
    static class Form{
        @NumberFormat(pattern = "###,###")
        private Integer number;

        @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime localDateTime;
    }
}
