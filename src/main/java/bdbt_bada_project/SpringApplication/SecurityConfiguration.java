package bdbt_bada_project.SpringApplication;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    //moje wypociny --/--
//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder managerBuilder) throws Exception {
//        managerBuilder.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("SELECT LOGIN AS USERNAME, PASSWORD, 'T'" +
//                        " FROM USERS WHERE LOGIN = ?")
//                .authoritiesByUsernameQuery("SELECT LOGIN AS USERNAME, ROLE AS authority " +
//                        "FROM USERS WHERE LOGIN = ?");
//
//    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("user")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("admin")
                .roles("ADMIN");
    }

    //moje wypociny --/--
/* ja haszuję
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("user")
                .roles("USER")

                .and()
                .withUser("admin")
                .password("admin")
                .roles("ADMIN");
    }*/
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//
//                .antMatchers("/delete/**", "/klienci", "/admin", "edit/**").access("hasRole('ROLE_ADMIN')")
//                .antMatchers("/main").access("hasRole('ROLE_USER')")
//                .antMatchers("/", "/index", "info", "testy", "/update").permitAll()
//                .antMatchers("/resources/static/**").permitAll()
//                .and()
//                .exceptionHandling().accessDeniedPage("/resources/static/js/script")
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .successHandler(myAuthenticationSuccessHandler())
//                .and()
//                .logout()
//                .logoutUrl("/index")
//                .logoutSuccessUrl("/index")
//                .permitAll();
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/resources/static/**").permitAll()
                .antMatchers("/main").authenticated()
                .antMatchers("/main_admin").access("hasRole('ADMIN')")
                .antMatchers("/main_user").access("hasRole('USER')")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/main")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/index")
                .logoutSuccessUrl("/index")
                .permitAll();
//                .antMatchers("/", "/index").permitAll()
//                .antMatchers("/resources/static/**").permitAll()
//                .antMatchers("/main").authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/index")
//                .logoutSuccessUrl("/index")
//                .permitAll();
    }


//    @Bean
//    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
//        return new MySimpleUrlAuthenticationSuccessHandler();
//    }

}
//przydatny link: https://www.journaldev.com/8748/spring-security-role-based-access-authorization-example
//package bdbt_bada_project.SpringApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import
//        org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
//
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .requestMatchers("/", "/index").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//
//
//    }
////@Bean
////public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////    http
////            .authorizeHttpRequests((authz) -> authz
////                    .anyRequest().authenticated()
////            )
////            .httpBasic(withDefaults());
////    return http.build();
////}
//
//
//    @Bean
//    public PasswordEncoder getPasswordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        //return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
//        return (web) -> web.ignoring().requestMatchers("/ignore1", "/ignore2");
//    }
//}