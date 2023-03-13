package com.webproject.config.security;

import com.webproject.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {
    private final JwtProvider jwtProvider;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        String token = getTokenFromRequest((HttpServletRequest) servletRequest);
//
//        if (token != null && jwtProvider.validateToken(token)) {
//            String userLogin = jwtProvider.getLoginFromToken(token);
//            UserDetails customUserDetails = userDetailsService.loadUserByUsername(userLogin);
//            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(auth);
//        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    protected String getTokenFromRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Cookie token = Arrays.stream(cookies).filter(el -> el.getName().equals("token")).findFirst().orElse(null);
            if (token != null) {
                return token.getValue();
            }
        }
        return null;
    }
}
