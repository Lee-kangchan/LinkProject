package com.chan.link.domain.user.controller;

import com.chan.link.domain.user.dto.LoginDto;
import com.chan.link.domain.user.dto.SignDto;
import com.chan.link.domain.user.dto.TokenDto;
import com.chan.link.domain.user.dto.UserUpdateDto;
import com.chan.link.global.jwt.JwtFilter;
import com.chan.link.global.jwt.TokenProvider;
import com.chan.link.domain.user.service.UserService;
import com.chan.link.global.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    @PostMapping("/login")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto){
        log.info("POST : authenticate " + loginDto.getEmail() + " pw :" + loginDto.getPassword() );
        //파라미터 email, pw를 이용하여 토큰 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        //토큰을 이용해서 Authentication 객체를 생성 authenticate 가 실행 될 때 loadUserByUsername 이 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //해당 객체를 SecurityContext에 저장하고 Authentication 객체를 createToken 메소드를 통해서 JWT Token 을 생성
        String jwt = tokenProvider.createToken(authentication);

        //JWT 를 해더에 저장
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);


        //토큰을 body 에도 담아서 보내기
        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }

    @GetMapping(value="/all")
    public ResponseEntity<List<UserVO>> userAll(){

        HttpHeaders httpHeaders = new HttpHeaders(); // 해더 생성
        List<UserVO> listUser = userService.TestUserAll(); //유저 불러오기
        ResponseEntity<List<UserVO>> response = new ResponseEntity(listUser, httpHeaders, HttpStatus.OK);
        log.info(response.toString());
        return response;
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<UserVO> getMyUserInfo(){
        log.info("getMyInfo : " );

        try {
            return ResponseEntity.ok(userService.getMyInfo().get());
        }catch (Exception e){
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{email}/")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<UserVO> getUserInfo(@PathVariable String email){
        log.info("getUserInfo : " + email);
        try {
            return ResponseEntity.ok(userService.getUserInfo(email).get());
        }catch (Exception e){
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    //회원 정보 수정 API
    public ResponseEntity<UserVO> updateUser(UserUpdateDto userUpdateDto){

        try {
            return ResponseEntity.ok(userService.userUpdateService(userUpdateDto));
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value= "/sign")
    //회원가입 API
    public ResponseEntity<UserVO> userSign(@Valid @RequestBody SignDto signDto) {
        try {
            return ResponseEntity.ok(userService.signService(signDto));
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
    }


}
