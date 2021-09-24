package com.chan.link.service;

import com.chan.link.repository.UserRepository;
import com.chan.link.vo.UserVO;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


//service에 각 역할 정리
@Service
@Log4j2
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserVO> TestUserAll() {
        List<UserVO> listUserVO = new ArrayList<>();
        // 모든 user정보를 불러온다 -> forEach로 list에 하나하나 값을 하나 씩 넣는다
        userRepository.findAll().forEach(e-> listUserVO.add(e) );
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
    public boolean signService(UserVO userVO) {
        UserVO sign = new UserVO();
        sign = userRepository.findByEmailAndPw(userVO.getEmail(), userVO.getPw());

        // Service 처리
        if(sign == null){ // email이 존재하지 않으면 회원가입 처리
            userRepository.save(userVO);
            return true;
        } else{ // 아니면 회원가입 안됨
            return false;
        }
    }

    @Override
    public boolean emailCheck(String email) {
        UserVO userVO = new UserVO();
        userRepository.findByEmail(email);
        userVO = userRepository.findByEmail(email);

        // Service 처리
        if(userVO.getEmail().isEmpty()){ // email이 존재하지 않으면 회원가입 처리
            userRepository.save(userVO);
            return true;
        } else{ // 아니면 회원가입 안됨
            return false;
        }
    }
}