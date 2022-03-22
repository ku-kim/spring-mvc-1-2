package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("hello-basic") // 모든 메서드 응답 받음
//    @RequestMapping("hello-basic/") // "hello-basic"와 "hello-basic/"는 다르지만 spring에서 매핑해줌
//    @RequestMapping({"hello-basic", "/hello-go"}) // 2개도 가능
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    // GET 메서드만
    @RequestMapping(value = "mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    /**
     * 편리한 축약 애노테이션 (코드보기)
     *
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    // GetMapping 따라가보면 사실 위 애노테이션과 동일
    @GetMapping("mapping-get-v2")
    public String mappingGet() {
        log.info("helloBasic");
        return "ok";
    }

    /*
     * PathVariable 사용
     * 변수명이 같으면 생략 가능
     * @PathVariable("userId") String userId -> @PathVariable userId
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
//    public String mappingPath(@PathVariable String userId) { // 변수명 동일하면 생략 가능
        log.info("mapping Path userId={}", data);
        return "ok";
    }

    /**
     * PathVariable 사용 다중
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long
            orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")  // GET /mapping-header?mode=debug 가 있어야 호출 됨
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug") // request header의 키,벨류가 mode: debug 방식 일 때만
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    @PostMapping(value = "/mapping-consume", consumes = "application/json") // headers= 로 쓰면 좋겠지만 미디어 타입은 따로 구분
    public String mappingConsumes() { // Content-Type: application/json
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html") // Accept: text/html
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
