package com.sylinx.springbootstudy.synccontroller;

import com.sylinx.springbootstudy.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/async")
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/test1")
    public String async1(@RequestParam("msg") String msg){
        asyncService.sendMsg(msg);
        System.out.println("async1 + " + msg);
        return "final_async1";
    }
}
