package connected.communication.controller;

import connected.communication.repository.Text;
import connected.communication.repository.freeboard.FreeBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * id : 글 번호
 * textName : 글 제목
 * textContent : 글 내용
 * recommendCnt : 글 추천 수
 * commnentCnt : 댓글 수
 * bookMarkCnt : 즐겨찾기 수
 */

@Slf4j
@Controller
@RequestMapping("/basic")
@RequiredArgsConstructor
public class FrontBoardController {
    private final FreeBoardRepository freeBoardRepository;

    //게시판 목록 조회 Controller
    @GetMapping("/freeboard/freeTexts")
    public String freeTexts(Model model) {
        List<Text> freeTexts = freeBoardRepository.findAll();
        model.addAttribute("freeTexts", freeTexts);
        return "basic/freeboard/freeTexts";
    }

    //게시판 게시글 조회 Controller
    @GetMapping("/freeboard/{textId}")
    public String text(@PathVariable Long textId, Model model){
        Text text = freeBoardRepository.findByTextId(textId);
        model.addAttribute("text",text);
        return "basic/text";
    }

    //=================================
    //게시글 저장기능 조회 및 저장 수행 Controller, html에서 action 미입력을 통한 자기이름 참조로 구현
    @GetMapping("/freeboard/addText")
    public String addText(){
        return "basic/freeboard/addText";
    }

    @PostMapping("/freeboard/addText")
    public String saveText(@ModelAttribute Text text, RedirectAttributes redirectAttributes){
        Text saveText = freeBoardRepository.save(text);
        redirectAttributes.addAttribute("textId", saveText.getId()); //RedirceAttributes를 활용한 PRG 기법, 인코더 결합 문제 해결 완료
        redirectAttributes.addAttribute("status", true); //redirect 성공시 수정 완료 출력을 위한 속성
        log.info("input sucess");
        return "redirect:/basic/freeboard/{textId}";
    }
    //=================================

    //게시글 수정 Controller
    @GetMapping("/freeboard/editText/{textId}/edit")
    public String editText(@PathVariable Long textId, Model model){
        Text text = freeBoardRepository.findByTextId(textId);
        model.addAttribute("text", text);
        return "basic/freeboard/editText";
    }

    //게시글 업데이터 Controllr
    @PostMapping("/freeboard/editText/{textId}/edit")
    public String reEdit(@PathVariable Long textId, @ModelAttribute Text text){
        freeBoardRepository.update(textId, text);
        return "redirect:/basic/freeboard/{textId}";
    }

    @PostConstruct
    public void init() {
        freeBoardRepository.save(new Text("테스트 글 첫 번째 제목입니다.", "테스트 글 첫 번째 내용 부분입니다", 1, 2, 3));
        freeBoardRepository.save(new Text("테스트 글 두 번째 제목입니다.", "테스트 글 첫 번째 내용 부분입니다", 1, 2, 3));
    }
}
