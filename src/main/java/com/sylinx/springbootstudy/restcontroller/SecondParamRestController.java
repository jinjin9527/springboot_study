package com.sylinx.springbootstudy.restcontroller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/secondcontoller")
public class SecondParamRestController {

    // param明示
    @GetMapping("/getparam1")
    public String firstGetMapping1(@RequestParam String id){
        return "「2 get param1」ID ：" + id;
    }

    // param書き換え
    @GetMapping("/getparam2")
    public String firstGetMapping2(@RequestParam(name = "id") String myId){
        return "「2 get param2」myId ：" + myId;
    }

    // paramオプション
    @GetMapping("/getparam3")
    public String firstGetMapping3(@RequestParam(required = false, name = "id") String myId){
        return "「2 get param3 option」myId ：" + myId;
    }

    // param java8特性
    @GetMapping("/getparam4")
    public String firstGetMapping4(@RequestParam(name = "id") Optional<String> myId){
        return "「2 get param4 java8」myId ：" + myId;
    }

    // param デフォルトバリュー
    @GetMapping("/getparam5")
    public String firstGetMapping5(@RequestParam(name = "id", defaultValue = "aaa") String myId){
        return "「2 get param5 default value」myId ：" + myId;
    }

    // param two params
    @GetMapping("/getparam6")
    public String firstGetMapping6(@RequestParam(name = "id") String myId, @RequestParam(name = "name") String myName) {
        return "「2 get param6 two params」myId： " + myId + "  myName：" + myName;
    }

    // all param
    @PostMapping("/getparam7")
    public String firstGetMapping7(@RequestParam Map<String, String> allParams) {
        return "「2 get param7 all params to map」" + allParams.entrySet();
    }

    // all param
    @DeleteMapping("/getparam8")
    public String firstGetMapping8(@RequestParam List<String> id) {
        return "「2 get param8 id List」" + id;
    }
}
