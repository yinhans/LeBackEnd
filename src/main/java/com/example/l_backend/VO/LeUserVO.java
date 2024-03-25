package com.example.l_backend.VO;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class LeUserVO {
    private String id;
    private String username;
    private String account;
    private String token;
}
