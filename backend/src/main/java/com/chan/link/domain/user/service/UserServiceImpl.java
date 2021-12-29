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
    public UserVO loginService(UserVO userVO) {
        UserVO login = new UserVO();
        // email 와 pw가 일치하는 db목록 들고오기
        login = userRepository.findByUserEmailAndUserPw(userVO.getUserEmail(), userVO.getUserPw());

        if(login.getUserEmail().isEmpty()){
            return null;
        }else{
            return login;
        }
    }


    @Override
    public UserVO signService(SignDto signDto) {
        if(userRepository.findOneWithAuthoritiesByUserEmail(signDto.getEmail()).orElse(null) != null){
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }
        signDto.UserPasswordEncoder(passwordEncoder.encode(signDto.getPw()));
        //유저 권한만 로그인
        UserVO user = signDto.toUser(signDto);
        return userRepository.save(user);
    }

    @Override
    public UserVO userUpdateService(UserUpdateDto userUpdateDto) {
        LocalDateTime dateTime = LocalDateTime.now();
        String userId = SecurityUtil.getCurrentUserId();
        UserVO user = userRepository.findByUserId(userId).get();
        userUpdateDto.UserPasswordEncoder(passwordEncoder.encode(userUpdateDto.getPw()));
        userUpdateDto.toUser(user, userUpdateDto);
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
