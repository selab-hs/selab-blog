package kr.ac.hs.selab.common.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {
    @GetMapping({"", "/", "index"})
    public String index() {
        return "fragments/index";
    }
}
