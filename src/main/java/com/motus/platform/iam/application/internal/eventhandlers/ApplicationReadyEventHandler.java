package com.motus.platform.iam.application.internal.eventhandlers;

import com.motus.platform.iam.domain.model.aggregates.UserAccount;
import com.motus.platform.iam.domain.model.entities.Role;
import com.motus.platform.iam.domain.model.valueobjects.RoleName;
import com.motus.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.motus.platform.iam.infrastructure.persistence.jpa.repositories.UserAccountRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Set;

@Component
public class ApplicationReadyEventHandler {
    private final RoleRepository roleRepository;
    private final UserAccountRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ApplicationReadyEventHandler(RoleRepository roleRepository, UserAccountRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void seedIdentityData() {
        Arrays.stream(RoleName.values()).forEach(name ->
                roleRepository.findByName(name).orElseGet(() -> roleRepository.save(new Role(name))));

        if (!userRepository.existsByEmailIgnoreCase("admin@telemtrix.com")) {
            var adminRole = roleRepository.findByName(RoleName.ROLE_FLEET_MANAGER).orElseThrow();
            userRepository.save(new UserAccount("Telemtrix Admin", "admin@telemtrix.com",
                    passwordEncoder.encode("password"), Set.of(adminRole)));
        }
    }
}