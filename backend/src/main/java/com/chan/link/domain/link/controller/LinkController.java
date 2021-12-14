package com.chan.link.domain.link.controller;

import com.chan.link.domain.link.dto.LinkDto;
import com.chan.link.domain.link.dto.PageDto;
import com.chan.link.domain.link.dto.ResponsePostDto;
import com.chan.link.domain.link.service.LinkService;
import com.chan.link.global.jwt.TokenProvider;
import com.chan.link.global.util.SecurityUtil;
import com.chan.link.global.vo.PostVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/link")
public class LinkController {

    private final LinkService linkService;

    @GetMapping("/all")
    public ResponseEntity<ResponsePostDto> linkAllList(PageDto pageDto){

        HttpHeaders httpHeaders = new HttpHeaders(); // 해더 생성
        return new ResponseEntity(linkService.LinkBestRecentAll(pageDto), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/best")
    public ResponseEntity<ResponsePostDto> linkBestList(PageDto pageDto){
        HttpHeaders httpHeaders = new HttpHeaders(); // 해더 생성
        return new ResponseEntity(linkService.LinkBestRecentAll(pageDto), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/user")
    public void linkUserList(){

    }
    @PostMapping("/user")
    public void linkAdd(@RequestBody LinkDto linkDto){
        linkService.LinkAdd(linkDto);
    }

    @PostMapping("/user/{id}/follow")
    public void linkFollow(@PathVariable String id){

    }


    @PutMapping("/user/{id}")
    public void linkUpdate(@PathVariable String id, LinkDto linkDto){
        linkService.LinkUpdate(id, linkDto);
    }

    @DeleteMapping("/user/{id}")
    public void linkDelete(@PathVariable String id){
        linkService.LinkDel(id);
    }
}
