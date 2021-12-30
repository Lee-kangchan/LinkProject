package com.chan.link.domain.user.service;

import com.chan.link.domain.user.dto.SignDto;
import com.chan.link.domain.user.dto.UserUpdateDto;
import com.chan.link.global.entity.Authority;
import com.chan.link.domain.user.repository.UserRepository;
import com.chan.link.global.util.SecurityUtil;
import com.chan.link.global.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.util.*;


//service에 각 역할 정리
@Service("UserService")
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional(readOnly = true)
    public List<UserVO> TestUserAll() {
        List<UserVO> listUserVO = new ArrayList<>();
        // 모든 user정보를 불러온다 -> forEach로 list에 하나하나 값을 하나 씩 넣는다
        userRepository.findAll().forEach(e-> {
            log.info(e.toString());
            listUserVO.add(e);
        } );
        return listUserVO;
    }


    @Override
    public UserVO signService(SignDto signDto) {
        if(userRepository.findOneWithAuthoritiesByUserEmail(signDto.getEmail()).orElse(null) != null){
            throw new RuntimeException();
        }
        signDto.UserPasswordEncoder(passwordEncoder.encode(signDto.getPw()));
        //유저 권한만 로그인
        UserVO user = signDto.toUser(signDto);
        return userRepository.save(user);
    }

    @Override
    public UserVO userUpdateService(UserUpdateDto userUpdateDto) {
        LocalDateTime dateTime = LocalDateTime.now();
        UserVO user = SecurityUtil.getCurrentUserId().flatMap(userRepository::findByUserId)
            .orElseThrow(() -> new RuntimeException());
        userUpdateDto.UserPasswordEncoder(passwordEncoder.encode(userUpdateDto.getPw()));
        user = userUpdateDto.toUser(user);
        log.info(user.toString());
        return userRepository.save(user);
    }

    @Override
    public boolean emailCheck(String email) {
        UserVO userVO = new UserVO();
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
    public Optional<UserVO> getMyInfo() {
        return SecurityUtil.getCurrentMemberId().flatMap(userRepository::findOneWithAuthoritiesByUserEmail);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserVO> getUserInfo(String email) {
        return userRepository.findOneWithAuthoritiesByUserEmail(email);
    }
}
