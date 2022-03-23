package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    // 1
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello");
        mav.addObject("data", "hello!");

        return mav;
    }

    //2
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "Hello v2!");
        return "response/hello";
    }

    // 3
    @RequestMapping("/response/hello") // 동적 컨텐츠 절대 경로와 동일, 권장하지 않음!
    public void responseViewV3(Model model) {
        model.addAttribute("data", "Hello v3!");
    }
}
