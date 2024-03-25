package com.example.l_backend.DTO;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Repository
@Data
public class LeUserDTO {
    private String username;
    private String password;
}
