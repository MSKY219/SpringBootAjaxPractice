package com.example.ajaxEx.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AjaxViewController {

    // 01_get 요청
    @GetMapping("/ajax_ex_01")
    public String ajaxEx01() {
        return "ajax_ex_01";
    }

    // 02_post 요청
    // @PostMapping("/ajax_ex_02") : 여기는 View를 연결해주는 컨트롤러이다.
    // Post 를 사용하면 405에러가 발생할 수 있다.
    @GetMapping("/ajax_ex_02")
    public String ajaxEx02() {
        return "ajax_ex_02";
    }

    // 03_get 요청 및 parameter 전달
    @GetMapping("/ajax_ex_03")
    public String ajaxEx03 () {
        return "ajax_ex_03";
    }

    // 04_post 요청 및 parameter 전달
    @GetMapping("/ajax_ex_04")
    public String ajaxEx04 () {
        return "ajax_ex_04";
    }

    // 05_get 요청 & parameter DTO로 받기(@ModelAttribute) & DTO 객체 리턴 하기
    @GetMapping("/ajax_ex_05")
    public String ajaxEx05 () {
        return "ajax_ex_05";
    }

    // 06_post 요청 & parameter DTO로 받기(@ModelAttribute) & DTO 객체 리턴 하기
    @GetMapping("/ajax_ex_06")
    public String ajaxEx06() {
        return "ajax_ex_06";
    }

    // 07_post 요청 & (JSON) parameter DTO로 받기(@RequestBody) & DTO 객체 리턴 하기
    @GetMapping("/ajax_ex_07")
    public String ajaxEx07() {
        return "ajax_ex_07";
    }

    // 08_post 요청 & (JSON) parameter 전달하기 & List 객체 리턴 하기
    @GetMapping("/ajax_ex_08")
    public String ajaxEx08() {
        return "ajax_ex_08";
    }

    // 09_post 요청 & parameter 전달하기 & ResponseEntity에 DTO 객체 리턴 하기
    @GetMapping("/ajax_ex_09")
    public String ajaxEx09() {
        return "ajax_ex_09";
    }

    // 10_post 요청 & parameter 전달하기 & ResponseEntity에 List 객체 리턴 하기
    @GetMapping("/ajax_ex_10")
    public String ajaxEx10() {
        return "ajax_ex_10";
    }
}
