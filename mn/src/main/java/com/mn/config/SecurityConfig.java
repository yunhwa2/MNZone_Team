package com.mn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.ui.Model;

@Configuration // Spring을 구성하기 위한 클래스
@EnableWebSecurity // 스프링 시큐리티를 활성화시키기 위한 어노테이션
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain( HttpSecurity http)throws Exception{

        http.formLogin()
                .loginPage("/members/login")//로그인 페이지 설정
                .defaultSuccessUrl("/")//로그인 성공 시 이동할 url
                .usernameParameter("id")//로그인시 사용할 이름
                .failureUrl("/members/login/error")//로그인 실패 시 보여줄 url
                .and()//뭉
                .logout()//logout처리
                .logoutRequestMatcher
                        (new AntPathRequestMatcher("/members/logout"))//로그아웃 url처리
                .logoutSuccessUrl("/");//로그아웃 성공 시 이동

        http.authorizeRequests()
                //모든 사용자가 인증 없이 접근 가능
                .mvcMatchers("/css/**","/js/**","/img/**").permitAll()
                .mvcMatchers("/static/**").permitAll()
                .mvcMatchers("/","/**","/missing/**","/mypet/**","/members/**","/item/**","/images/**","/Jiseong/**","/notice/**").permitAll()
                //admin으로 시작하는 경로는 admin만 가능
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();
        http.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());

        //httpsecurity 객체를 매개변수로 받아서 해당 객체를 사용하여 필터 체인을 구성하고 반환
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        //비밀번호를 암호화
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    )throws Exception{
        return authenticationConfiguration.getAuthenticationManager();

    }
}
