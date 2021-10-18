package com.chan.link.service;

import com.chan.link.dto.SignDto;
import com.chan.link.entity.Authority;
import com.chan.link.repository.UserRepository;
import com.chan.link.util.SecurityUtil;
import com.chan.link.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


//service에 각 역할 정리
@Service("UserService")
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
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
        login = userRepository.findByEmailAndPw(userVO.getEmail(), userVO.getPw());

        if(login.getEmail().isEmpty()){
            return null;
        }else{
            return login;
        }
    }


    @Override
    public UserVO signService(SignDto signDto) {
        if(userRepository.findOneWithAuthoritiesByEmail(signDto.getEmail()).orElse(null) != null){
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }
        LocalDateTime dateTime = LocalDateTime.now();
        Authority authority = Authority.builder().authority_name("ROLE_USER").build();

        UserVO user = UserVO.builder().email(signDto.getEmail())
                .pw(passwordEncoder.encode(signDto.getPw()))
                .gender(signDto.getGender())
                .name(signDto.getName())
                .phone(signDto.getPhone())
                .nickname(signDto.getNickname())
                .authorities(Collections.singleton(authority))
                .create_at(dateTime)
                .modified_at(dateTime)
                .activated(true)
                .build();
        return userRepository.save(user);
    }

    @Override
    public boolean emailCheck(String email) {
        UserVO userVO = new UserVO();
        userRepository.findByEmail(email);
        userVO = userRepository.findByEmail(email)
                .get();
        // Service 처리
        if(userVO.getEmail().isEmpty()){ // email이 존재하지 않으면 회원가입 처리
            userRepository.save(userVO);
            return true;
        } else{ // 아니면 회원가입 안됨
            return false;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserVO> getMyInfo() {
        return SecurityUtil.getCurrentMemberId().flatMap(userRepository::findOneWithAuthoritiesByEmail);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserVO> getUserInfo(String email) {
        return userRepository.findOneWithAuthoritiesByEmail(email);
    }
}
