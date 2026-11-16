package com.motus.platform.profiles.domain.model.aggregates;

import com.motus.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "profiles")
public class Profile extends AuditableAbstractPersistenceEntity {
    @Column(nullable = false, unique = true)
    private Long userId;
    @Column(nullable = false, length = 120)
    private String fullName;
    @Column(nullable = false, unique = true, length = 160)
    private String email;
    @Column(length = 30)
    private String phoneNumber;
    @Column(length = 120)
    private String companyName;

    public Profile(Long userId, String fullName, String email, String phoneNumber, String companyName) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
    }
}