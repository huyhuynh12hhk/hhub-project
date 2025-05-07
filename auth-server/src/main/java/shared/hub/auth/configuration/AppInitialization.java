package shared.hub.auth.configuration;//package lab.start.auth_server.configuration;
//
//import lab.start.auth_server.constant.Roles;
//import lab.start.auth_server.model.entity.AppUser;
//import lab.start.auth_server.model.entity.Role;
//import lab.start.auth_server.repository.AppUserRepository;
//import lab.start.auth_server.repository.RoleRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.HashSet;
//import java.util.List;
//
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class AppInitialization {
//    @Bean
//    @ConditionalOnProperty(
//            prefix = "spring",
//            value = "datasource.driverClassName",
//            havingValue = "com.mysql.cj.jdbc.Driver")
//    ApplicationRunner seedRole(RoleRepository roleRepository) {
//        log.info("Init role.....");
//        return args -> {
//            if (roleRepository.findAll().isEmpty()) {
//                roleRepository.save(Role.builder()
//                        .name(Roles.ROLE_USER)
//                        .description("User role")
//                        .build());
//
////                Role adminRole =
//                roleRepository.save(Role.builder()
//                        .name(Roles.ROLE_ADMIN)
//                        .description("Admin role")
//                        .build());
//
//            }
//            log.info("Roles initialization completed .....");
//        };
//    }
//
//    @Bean
//    @ConditionalOnProperty(
//            prefix = "spring",
//            value = "datasource.driverClassName",
//            havingValue = "com.mysql.cj.jdbc.Driver")
//    ApplicationRunner seedUser(AppUserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
//        log.info("Init role.....");
//        return args -> {
//            if (userRepository.findAll().isEmpty()) {
//                var role = roleRepository.findByName(Roles.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("No role exist"));
//
//                userRepository.save(
//                        AppUser
//                                .builder()
//                                .email("admin@example.com")
//                                .username("admin")
//                                .password(passwordEncoder.encode("123456"))
//                                .active(true)
//                                .roles(new HashSet<>(List.<Role>of(role)))
//                                .image("")
//                                .build()
//                );
//
//
//                log.warn(
//                        "Admin user has been created with default password, for security reason please change it now!");
//            }
//            log.info("User initialization completed .....");
//        };
//    }
//
//
//}
