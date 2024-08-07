package ru.geekbrains.java.newproject.security;


//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//
//import java.util.List;
//
//@Component
//public class MyCustomUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//    private final UserRoleRepository roleRepository;
//
//    public MyCustomUserDetailsService(UserRepository userRepository, UserRoleRepository roleRepository) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user  = userRepository.findByLogin(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: " + username));
//
//        List<SimpleGrantedAuthority> userRoles = roleRepository.findUserRoleById(user.getId()).stream()
//                .map(it -> new SimpleGrantedAuthority(it.getName()))
//                .toList();
//        return new org.springframework.security.core.userdetails.User(
//                user.getLogin(),
//                user.getPassword(),
//                userRoles
//        );
//    }
//}
