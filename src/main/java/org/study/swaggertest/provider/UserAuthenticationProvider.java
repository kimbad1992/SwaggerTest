package org.study.swaggertest.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.study.swaggertest.dto.UserDetail;
import org.study.swaggertest.entity.User;
import org.study.swaggertest.service.UserService;

@Component
public class UserAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    public UserAuthenticationProvider(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.setUserDetailsService(userDetailsService);
        this.setPasswordEncoder(bCryptPasswordEncoder);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserDetail userDetail = (UserDetail) userService.loadUserByUsername(username);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String dbPassword = userDetail.getPassword(); // 이미 암호화된 값

        if (!encoder.matches(password, dbPassword)) { // 비밀번호 원본과 암호화된 비밀번호를 대조
            throw new BadCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        User user = userDetail.getUser();

        if (user == null || !user.getEnabled()) {
            throw new BadCredentialsException("사용할 수 없는 계정입니다.");
        }

        return new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
