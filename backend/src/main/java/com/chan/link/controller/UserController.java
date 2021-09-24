package com.chan.link.controller;

import com.chan.link.service.UserService;
import com.chan.link.vo.UserVO;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @GetMapping(value="/all")
    public ResponseEntity<List<UserVO>> userAll(){

        HttpHeaders httpHeaders = new HttpHeaders();
        List<UserVO> listUser = userService.TestUserAll();
        ResponseEntity<List<UserVO>> response = new ResponseEntity(listUser, httpHeaders, HttpStatus.OK);
        log.info(response.toString());
        return response;
    }

    @PostMapping(value= "/sign")
    public ResponseEntity<Boolean> userSign(@RequestBody UserVO userVO){
        log.info(userVO.getEmail());
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity(userService.signService(userVO),httpHeaders,HttpStatus.OK);
    }

    @PostMapping(value="login")
    public ResponseEntity<Boolean> userLogin(@RequestBody UserVO userVO){
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            ResponseEntity<Boolean> response = new ResponseEntity(userService.loginService(userVO), httpHeaders, HttpStatus.OK);
            return response;
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);

        }
    }


}
