package com.example.usrservice.configure;

import com.example.usrservice.controller.MyUserDetailsService;
import com.example.usrservice.util.MyPasswordEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启security注解
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(MyPasswordEncoder.getEncoder());
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        //解决静态资源被拦截的问题
//        web.ignoring().antMatchers("/somewhere/**");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //允许所有用户访问"/"和"/home"
        http.httpBasic()
                .and()
                .cors()
                .and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/home2").permitAll()
                    .antMatchers("/home").permitAll()
                    .antMatchers(HttpMethod.POST, "/signin").permitAll()
                    //需要注释掉
                    .antMatchers(HttpMethod.POST, "/v1/*").permitAll()
                    //.antMatchers("/v1/*").hasAuthority("user")
                .and()
                .formLogin()
                    //指定登录页是"/login"
                    //登录成功后默认跳转到
                    .successHandler(new AuthenticationSuccessHandler() {
                        @Override
                        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                            response.setContentType("application/json;charset=utf-8");
                            PrintWriter writer = response.getWriter();
//                            response.setStatus(200);
                            ObjectMapper om = new ObjectMapper();
                            String successMsg = om.writeValueAsString(om.writeValueAsString(authentication));
                            writer.write(successMsg);
                            writer.flush();
                            writer.close();
                        }
                    })
                    .permitAll()
                .and()
                .logout()
                    .logoutUrl("/logout")
                    //退出登录后的默认url是"/login"
                    .logoutSuccessUrl("/login")
                    .permitAll();
        //解决非thymeleaf的form表单提交被拦截问题
        http.csrf().disable();

        //解决中文乱码问题
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
    }
}
