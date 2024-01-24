package com.example.montaneralbertomyikea.services;

import com.example.montaneralbertomyikea.models.Role;
import com.example.montaneralbertomyikea.models.User;
import com.example.montaneralbertomyikea.repositories.RoleRepository;
import com.example.montaneralbertomyikea.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    /*@Autowired
    private PasswordEncoder passwordEncoder;*/

    public void saveUser(User user) {
        Role role = roleRepository.findByNombreRol("ROLE_USER");

        if (role == null)
            role = roleRepository.save(new Role("ROLE_USER"));

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        ArrayList<Role> roles= new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getEmail()
                    , user.getPassword(),
                    user.getRoles().stream()
                            .map((role) -> new SimpleGrantedAuthority(role.getNombreRol()))
                            .collect(Collectors.toList()));
        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
    public List<User> listarUsuarios(){return userRepository.findAll();}
    public void addRoleManager(String email){
        User user = userRepository.findByEmail(email);
        Role role = roleRepository.findByNombreRol("ROLE_MANAGER");

        if (role == null)
            role = roleRepository.save(new Role("ROLE_MANAGER"));
        List<Role> roles= user.getRoles();
        if(roles!=null && !roles.contains(role)) {
            roles.add(role);
            user.setRoles(roles);
            userRepository.save(user);
        }
    }
    public void addRoleAdmin(String email){
        User user = userRepository.findByEmail(email);
        Role role = roleRepository.findByNombreRol("ROLE_ADMIN");

        if (role == null)
            role = roleRepository.save(new Role("ROLE_ADMIN"));
        List<Role> roles= user.getRoles();
        if(roles!=null) {
            roles.add(role);
            user.setRoles(roles);
            userRepository.save(user);
        }
    }

    public void deleteUserById(int id) {userRepository.deleteById(id);}
}
