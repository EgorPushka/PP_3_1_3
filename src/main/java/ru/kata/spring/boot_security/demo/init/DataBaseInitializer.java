package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepo;
import ru.kata.spring.boot_security.demo.repositories.UsersRepo;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;

@Component
public class DataBaseInitializer {

    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DataBaseInitializer(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Transactional
    public void initializeData() {

        Role userRole = new Role("ROLE_USER");
        roleService.save(userRole);
        User user = new User("user", passwordEncoder.encode("user"), "luke.skywalker@gmail.com", 23);
        user.setRoles(Arrays.asList(userRole));
        userService.add(user);

        Role adminRole = new Role("ROLE_ADMIN");
        roleService.save(adminRole);
        User admin = new User("admin", passwordEncoder.encode("admin"), "john.snow@winterfall.com", 31);
        admin.setRoles(Arrays.asList(adminRole));
        userService.add(admin);

        Role managerRole = new Role("AUTH_MANAGERS");
        roleService.save(managerRole);
        User manager = new User("manager", passwordEncoder.encode("manager"), "neo.anderson@gmail.com", 32);
        manager.setRoles(Arrays.asList(managerRole));
        userService.add(manager);

    }

}