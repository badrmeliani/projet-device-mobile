package com.valueit.device.configuration;

import com.valueit.device.jwt.AuthEntryPointJwt;
import com.valueit.device.jwt.AuthTokenFilter;
import com.valueit.device.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private IUserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        http.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/").permitAll();
   http.authorizeRequests().antMatchers("/login").permitAll();
     http.authorizeRequests().antMatchers("/auth/**").permitAll();


//
        http.authorizeRequests().antMatchers("/users/sort/**").hasAuthority("sort_users");
        http.authorizeRequests().antMatchers("/users/pagination/**").hasAuthority("pagination_users");
     http.authorizeRequests().antMatchers("/users/read/**").hasAuthority("view_users");
     http.authorizeRequests().antMatchers("/users/create/**").hasAuthority("create_users");
      http.authorizeRequests().antMatchers("/users/update/**").hasAuthority("update_users");
      http.authorizeRequests().antMatchers("/users/delete/**").hasAuthority("delete_users");

//                http.authorizeRequests().antMatchers("/article/update/**").hasAuthority("update_article");
//        http.authorizeRequests().antMatchers("/article/delete/**").hasAuthority("delete_article");
//        http.authorizeRequests().antMatchers("/article/read/**").hasAuthority("read_article");

//        http.authorizeRequests().antMatchers("/auth/**").permitAll();
//        http.authorizeRequests().antMatchers("/admin/role").hasAnyAuthority("ADMIN");
//
//        http.authorizeRequests().antMatchers("/welcome").permitAll();
//        http.authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN","SUPERADMIN");
//        http.authorizeRequests().antMatchers("/admin/user/view").hasAuthority("CHEF");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/h2/**","/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
}
