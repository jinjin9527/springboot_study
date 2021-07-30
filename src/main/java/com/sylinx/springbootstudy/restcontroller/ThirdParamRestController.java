package com.sylinx.springbootstudy.restcontroller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/thirdcontoller")
public class ThirdParamRestController {

    // param明示
    @GetMapping("/getparam1/{id}")
    public String firstGetMapping1(@PathVariable  String id){
        return "「3 get param1」ID ：" + id;
    }

    // param書き換え
    @GetMapping("/getparam2/{id}")
    public String firstGetMapping2(@PathVariable(name = "id") String myId){
        return "「3 get param2」myId ：" + myId;
    }

    // paramオプション
    @GetMapping(value={"/getparam3", "/getparam3/{id}"})
    public String firstGetMapping3(@PathVariable(required = false, name = "id") String myId){
        return "「3 get param3 option」myId ：" + myId;
    }

    // param java8特性
    @GetMapping(value={"/getparam4", "/getparam4/{id}"})
    public String firstGetMapping4(@PathVariable(name = "id") Optional<String> myId){
        return "「3 get param4 java8」myId ：" + myId;
    }

    // param デフォルトバリュー
//    @GetMapping("/getparam5/{id}")
//    public String firstGetMapping5(@PathVariable(name = "id", defaultValue = "aaa") String myId){
//        return "「3 get param5 default value」myId ：" + myId;
//    }

    // param two params
    @GetMapping("/getparam6/{id}/{name}")
    public String firstGetMapping6(@PathVariable(name = "id") String myId, @PathVariable(name = "name") String myName) {
        return "「3 get param6 two params」myId： " + myId + "  myName：" + myName;
    }

    // all param
    @PostMapping("/getparam7/{id}/{name}")
    public String firstGetMapping7(@PathVariable Map<String, String> allParams) {
        return "「3 get param7 all params to map」" + allParams.entrySet();
    }

    // all param
    @DeleteMapping("/getparam8/{id}/{name}")
    public String firstGetMapping8(@PathVariable List<String> id) {
        return "「3 get param8 id List」" + id;
    }
}
