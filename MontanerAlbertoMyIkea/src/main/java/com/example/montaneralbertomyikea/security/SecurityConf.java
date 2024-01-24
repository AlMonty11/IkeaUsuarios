package com.example.montaneralbertomyikea.security;

import com.example.montaneralbertomyikea.models.Role;
import com.example.montaneralbertomyikea.models.User;
import com.example.montaneralbertomyikea.repositories.RoleRepository;
import com.example.montaneralbertomyikea.repositories.UserRepository;
import com.example.montaneralbertomyikea.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConf {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) ->authz
                        .requestMatchers("/","/register","/logout").permitAll()
                        .anyRequest().authenticated()
                );
        http.formLogin(form -> form
                .loginPage("/login")
                /*.failureUrl("/login-error")*/
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .permitAll()
        );

        http.logout(form -> form
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
        );
        http.userDetailsService(userService);
        return http.build();

    }
    @Bean
    public List<User> seedUsersAndRoles() {
        //Creo Roles
        if (roleRepository.findByNombreRol("ROLE_USER") == null) roleRepository.save(new Role("ROLE_USER"));
        if (roleRepository.findByNombreRol("ROLE_MANAGER") == null) roleRepository.save(new Role("ROLE_MANAGER"));
        if (roleRepository.findByNombreRol("ROLE_ADMIN") == null) roleRepository.save(new Role("ROLE_ADMIN"));
        List<User> users = new ArrayList<>();
        try{
            //Creo usuario/os
            User userAdmin = new User("admin",
                    "admin@myikea.com",
                    new BCryptPasswordEncoder().encode("Asdf1234!"),
                    List.of(roleRepository.findByNombreRol("ROLE_ADMIN")));
            if(userRepository.findByEmail("admin@myikea.com")==null){
                userRepository.save(userAdmin);
                users.add(userAdmin);
            }

            User userManager = new User("manager",
                    "manager@myikea.com",
                    new BCryptPasswordEncoder().encode("Asdf1234!"),
                    List.of(roleRepository.findByNombreRol("ROLE_MANAGER")));
            if(userRepository.findByEmail("manager@myikea.com")==null) {
                userRepository.save(userManager);
                users.add(userManager);
            }
            User userUSER = new User("user",
                    "manager@myikea.com",
                    new BCryptPasswordEncoder().encode("Asdf1234!"),
                    List.of(roleRepository.findByNombreRol("ROLE_USER")));
            if(userRepository.findByEmail("user@myikea.com")==null) {
                userRepository.save(userManager);
                users.add(userManager);
            }

        }catch(Exception e){
            System.out.println("Ese usuario ya estaba creado");
        }
        return users;
    }
}
