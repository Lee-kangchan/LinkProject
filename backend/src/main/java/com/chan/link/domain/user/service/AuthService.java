package com.chan.link.domain.user.service;

import com.chan.link.domain.user.repository.UserRepository;
import com.chan.link.global.entity.AuthUserEntity;
import com.chan.link.global.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("UserDetailsService")
@RequiredArgsConstructor
@Slf4j
public class AuthService implements UserDetailsService {


    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        log.info("loadUserByUserName ing.. email : " + email);
        try{

            return userRepository.findOneWithAuthoritiesByEmail(email)
                    .map(user -> createUser(email, user))
                    .orElseThrow(() -> new UsernameNotFoundException(email + " -> DB에서 찾을 수 없습니다."));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private AuthUserEntity createUser(String email, UserVO userVO) {

        log.info("createUser Method ing.. UserVO : " + userVO.toString());
        if(!userVO.isActivated()){
            throw new RuntimeException(email + " -> 활성화되어 있지 않습니다.");
        }
        List<GrantedAuthority> grantedAuthorities = userVO.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());
        return new AuthUserEntity(userVO.getEmail(), userVO.getPw(), userVO.getSeq(),grantedAuthorities);
    }

}
