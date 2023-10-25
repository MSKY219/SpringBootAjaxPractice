package com.example.ajaxEx.controller;

import com.example.ajaxEx.dto.AjaxDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AjaxController {

    // 01_get 요청
    @GetMapping("/ex01")
    public String ex01() {
        System.out.println("01_get 요청");
        return "index";
        // ajax로 호출을 하게 되면 기존 스프링의 viewResolver가 작동은 하지만,
        // 처리 결과를 화면에서 출력하지 않고 ajax를 통해 결과 데이터가 되돌아간다.
        // 화면 전환으로 접근해서는 안된다.
    }

    // 02_post 요청
    @PostMapping("/ex02")
    public @ResponseBody String ex02() {
        // @ResponseBody : 리턴하는 값을 body에 담아서 보내주는 역할을 한다.
        System.out.println("02_post 요청");
        return "안녕하세요.";
        // ex01과 다르게, 콘솔에 리턴 값인 "index"가 출력된다.
        // return "안녕하세요." 를 입력하면 콘솔에는 "안녕하세요."가 리턴되어 출력된다.
        //
    }

    // 03_get 요청 및 parameter 전달
    @GetMapping("/ex03")
    public @ResponseBody String ex03(@RequestParam("param1") String param1, @RequestParam("param2") String param2) {
        System.out.println("ex03 / param1 = " + param1 + ", param2 = " + param2);
        return "ex03 메서드 호출 완료";
    }

    // 04_post 요청 및 parameter 전달
    @PostMapping("/ex04")
    public @ResponseBody String ex04(@RequestParam("param1") String param1, @RequestParam("param2") String param2) {
        System.out.println("ex04 / param1 = " + param1 + ", param2 = " + param2);
        return "ex04 메서드 호출 완료";
    }

    // 05_get 요청 & parameter DTO로 받기(@ModelAttribute) & DTO 객체 리턴 하기
    @GetMapping("/ex05")
    // public @ResponseBody String ex05(@ModelAttribute AjaxDTO ajaxDTO) {
    public @ResponseBody AjaxDTO ex05(@ModelAttribute AjaxDTO ajaxDTO) {
        // 파라미터들을 개별로 받을때는 @RequestParam을 사용하였다.
        // 스프링에서는 @ModelAttribute 어노테이션을 사용해 DTO로 곧바로 값을 전달할 수 있다.
        // @ModelAttribute을 사용하기 위해 전제조건이 필요한데,
        // name 속성의 이름과 DTO의 필드 이름이 정확히 일치해야 스프링이 이름에 맞춰 값을 담아서 DTO 객체를 만들어 준다.
        // 즉, 파라미터의 이름만 맞다면, 스프링이 많은 절차를 처리해준다.
        // @ModelAttribute 은 생략할 수 있지만, 익숙하지 않기 때문에 추가하였음.
        System.out.println("ex05 / ajaxDTO = " + ajaxDTO);
        // return "ex05 메서드 호출 완료";
        return ajaxDTO;
        /*
        성공 시,ajax의 log에서 res의 출력으로 전달된 객체를 확인할 수 있다.
        자바 객체가 자바 스크립트 객체로 바뀐다.
        {param1: 'Hello SpringBoot..!', param2: '안녕'}
        param1
        :
        "Hello SpringBoot..!"
        param2
        :
        "안녕"
       */
    }

    // 06_post 요청 & parameter DTO로 받기(@ModelAttribute) & DTO 객체 리턴 하기
    @PostMapping("/ex06")
    public @ResponseBody AjaxDTO ex06(@ModelAttribute AjaxDTO ajaxDTO) {
        System.out.println("ex06 / ajaxDTO = " + ajaxDTO);
        return ajaxDTO;
    }

    // 07_post 요청 & (JSON) parameter DTO로 받기(@RequestBody) & DTO 객체 리턴 하기
    @PostMapping("/ex07")
    public @ResponseBody AjaxDTO ex07(@RequestBody AjaxDTO ajaxDTO) {
        //ResponseBody와 RequestBody의 차이는 요청할 때와 요청 받을 때로 생각할 수 있다.
        System.out.println("ex07 / ajaxDTO = " + ajaxDTO);
        return ajaxDTO;
    }

    // 08_post 요청 & (JSON) parameter 전달하기 & List 객체 리턴 하기
    private List<AjaxDTO> DTOList() {
        List<AjaxDTO> dtoList = new ArrayList<>();
        dtoList.add(new AjaxDTO("data1", "data11"));
        dtoList.add(new AjaxDTO("data2", "data22"));
        return dtoList;
    }

    @PostMapping("/ex08")
    public @ResponseBody List<AjaxDTO> ex08(@RequestBody AjaxDTO ajaxDTO) {
        // AjaxDTO객체가 담긴 리스트이기 때문에 List<AjaxDTO>로 바꿔준다.
        // List<AjaxDTO> 위치에는 모든 오브젝트 타입이 들어갈 수 있다.
        System.out.println("ex08 / ajaxDTO = " + ajaxDTO);
        List<AjaxDTO> dtoList = DTOList();
        dtoList.add(ajaxDTO);
        return dtoList;
        /*
        아래와 같이 콘솔에 출력된다.
        (3) [{…}, {…}, {…}]
        0:{param1: 'data1', param2: 'data11'}
        1:{param1: 'data2', param2: 'data22'}
        2:{param1: 'Hello SpringBoot..!', param2: '안녕'}
        length:3
        * */
    }

    // 09_post 요청 & parameter 전달하기 & ResponseEntity에 DTO 객체 리턴 하기
    @PostMapping("/ex09")
    public ResponseEntity ex09 (@RequestBody AjaxDTO ajaxDTO) {
        // ResponseEntity는 body뿐만 아니라, header와 status 등을 함께 전달할 수 있다.
        // ResponseBody보다 더 포괄적이다.
        System.out.println("ex09 / ajaxDTO = " + ajaxDTO);
        // return new ResponseEntity<>(HttpStatus.OK);
        // 위 코드는 데이터를 리턴하는게 아닌 상태를 반환한다.
        // return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT); // 만우절 농담용 에러 출력
        return new ResponseEntity<>(ajaxDTO, HttpStatus.OK);
    }

    // 10_post 요청 & parameter 전달하기 & ResponseEntity에 List 객체 리턴 하기
    @PostMapping("ex10")
    public ResponseEntity ex10 (@RequestBody AjaxDTO ajaxDTO) {
        System.out.println("ajaxDTO = " + ajaxDTO);
        List<AjaxDTO> dtoList = DTOList();
        dtoList.add(ajaxDTO);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }
}