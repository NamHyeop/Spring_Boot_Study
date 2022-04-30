package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/hello-basic" , method = RequestMethod.GET)
    //2개도 가능하다
    //@RequestMapping({"/hello-basic", "/hello-go"})
    public String helloBasic(){
        log.info("helloBasic");
        return "OK";
    }

    /**
     * 편리한 축약 애노테이션 (코드보기) * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }

    /**
     *
     * PathaVariable 사용
     * 변수명이 같으면 생략이 가능하다.
     *
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data){
        log.info("mapping path {}", data);
        return "OK";
    }

    //생략되는 경우. 뭔말인지 이해 못했는데 코드 보니 바로 이해 가능
//    @RequestMapping("/mapping/{userId}")
//    //매개변수 부분을 보자 이름이 같아서 생략한걸 볼 수 있다.
//    public String mappingPath(@PathVariable String userId){
//        log.info("mapping path {}", userId);
//        return "OK"
//    }

    /**
     * pathValue 사용 다중
     */

    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId){
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "OK";
    }

    @GetMapping("{userId}/{orderId}")
    public String mappingPath2(@PathVariable String userId, @PathVariable Long orderId){
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "OK";
    }

    /**
     * 파라미터로 추가 매핑
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * HTTP 통신이 들어오는 데이터 유형을 설정가능함.
     * consumnes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    //현재 예시는 JSON일 경우만 통신 가능
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes(){
        log.info("mappingConsumes");
        return "OK";
    }

    /**
     * http의 설정정보에 Acppet로 설정된것이 Produce와 일치하는 경우에만 통신 가능
     * (중요) Controller가 Produce로 HTML형식을 생산하기로 정했기 때문에 Accept를 지정해서 HTML만 들어올수 있게 지정한거다.
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*
     */

    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduces(){
        log.info("mappingProduces");
        return "ok";
    }
}
