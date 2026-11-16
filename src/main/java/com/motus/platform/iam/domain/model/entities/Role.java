package com.motus.platform.iam.domain.model.entities;

import com.motus.platform.iam.domain.model.valueobjects.RoleName;
import com.motus.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "roles")
public class Role extends AuditableAbstractPersistenceEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, length = 40)
    private RoleName name;

    public Role(RoleName name) {
        this.name = name;
    }
}