package in.codingage.blooms.controller;



// annotation - going to very important part of spring boot
// using spring boot for backend development
// spring - very configuration heavy framework

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/ping")
    public String ping(){
        return "ok";
    }
}
