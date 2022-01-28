package com.chan.link.domain.user.service;

import com.chan.link.domain.user.dto.UserDto;
import com.chan.link.domain.user.repository.UserRepository;
import com.chan.link.global.jwt.TokenProvider;
import com.chan.link.global.util.SecurityUtil;
import com.chan.link.global.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;


//service에 각 역할 정리
@Service("UserService")
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Override
    @Transactional(readOnly = true)
    public List<User> TestUserAll() {
        List<User> listUserVO = new ArrayList<>();
        // 모든 user정보를 불러온다 -> forEach로 list에 하나하나 값을 하나 씩 넣는다
        userRepository.findAll().forEach(e-> {
            log.info(e.toString());
            listUserVO.add(e);
        } );
        return listUserVO;
    }

    @Override
    public String tokenGenerationService(UserDto.Login loginDto) {

        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

            //토큰을 이용해서 Authentication 객체를 생성 authenticate 가 실행 될 때 loadUserByUsername 이 실행
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return tokenProvider.createToken(authentication);
        }
        catch (BadCredentialsException e){
             throw new BadCredentialsException("");
        }
    }

    @Override
    public User signService(UserDto.Sign signDto) {
        if(userRepository.findOneWithAuthoritiesByUserEmail(signDto.getEmail()).orElse(null) != null){
            throw new RuntimeException();
        }
        signDto.UserPasswordEncoder(passwordEncoder.encode(signDto.getPw()));
        //유저 권한만 로그인
        User user = signDto.toUser();
        return userRepository.save(user);
    }

    @Override
    public User userUpdateService(UserDto.Update userUpdateDto) {
        LocalDateTime dateTime = LocalDateTime.now();
        User user = SecurityUtil.getCurrentUserId().flatMap(userRepository::findByUserId)
            .orElseThrow(() -> new RuntimeException());
        userUpdateDto.UserPasswordEncoder(passwordEncoder.encode(userUpdateDto.getPw()));
        user = userUpdateDto.toUser(user);
        log.info(user.toString());
        return userRepository.save(user);
    }

    @Override
    public boolean emailCheck(String email) {
        User userVO = new User();
        userVO = userRepository.findByUserEmail(email)
                .get();
        // Service 처리
        if(userVO.getUserEmail().isEmpty()){ // email이 존재하지 않으면 회원가입 처리
            userRepository.save(userVO);
            return true;
        } else{ // 아니면 회원가입 안됨
            return false;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getMyInfo() {
        return SecurityUtil.getCurrentMemberId().flatMap(userRepository::findOneWithAuthoritiesByUserEmail);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserInfo(String email) {
        return userRepository.findOneWithAuthoritiesByUserEmail(email);
    }
}
