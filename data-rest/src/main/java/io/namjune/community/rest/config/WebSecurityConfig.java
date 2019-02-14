package io.namjune.community.rest.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    InMemoryUserDetailsManager userDetailsManager() {
        User.UserBuilder commonUser = User.withUsername("commonUser").password("{noop}common")
            .roles("USER");
        User.UserBuilder nj = User.withUsername("nj").password("{noop}1234").roles("USER", "ADMIN");

        List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList.add(commonUser.build());
        userDetailsList.add(nj.build());

        return new InMemoryUserDetailsManager(userDetailsList);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin(CorsConfiguration.ALL);
        configuration.addAllowedMethod(CorsConfiguration.ALL);
        configuration.addAllowedHeader(CorsConfiguration.ALL);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        http.httpBasic()
            .and().authorizeRequests()
            .anyRequest().permitAll()
            .and().cors().configurationSource(source)
            .and().csrf().disable();
    }
}
