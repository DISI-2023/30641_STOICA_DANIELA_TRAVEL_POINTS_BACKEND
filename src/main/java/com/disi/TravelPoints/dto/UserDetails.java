package com.disi.TravelPoints.dto;

import com.disi.TravelPoints.enums.RoleName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetails {
    private Long id;
    private String name;
    private String email;
    private RoleName role;
}
