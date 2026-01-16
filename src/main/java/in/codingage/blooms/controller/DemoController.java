package in.codingage.blooms.controller;



// annotation - going to very important part of spring boot
// using spring boot for backend development
// spring - very configuration heavy framework

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping("/")
    public String demo(){
        return "Hello from Blooms Application! You Sent";
    }
    @RequestMapping("/demo")
    public String getDemo(){
        return "Hi from get demo!";
    }
}
