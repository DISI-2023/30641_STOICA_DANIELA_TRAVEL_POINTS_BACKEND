package com.disi.TravelPoints.model;

import com.disi.TravelPoints.enums.RoleName;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private RoleName name;
    @OneToMany(mappedBy="role", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<User> users;
}
