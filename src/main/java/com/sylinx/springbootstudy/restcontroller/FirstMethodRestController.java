package com.sylinx.springbootstudy.restcontroller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/firstcontoller")
public class FirstMethodRestController {

    @GetMapping("/helloget")
    public String firstGetMapping(){
        return "1 get mapping";
    }

    @PostMapping("/hellopost")
    public String firstPostMapping(){
        return "1 post mapping";
    }

    @DeleteMapping("/hellodelete")
    public String firstDeleteMapping(){
        return "1 delete mapping";
    }

    @PutMapping("/helloput")
    public String firstPutMapping(){
        return "1 put mapping";
    }
}
