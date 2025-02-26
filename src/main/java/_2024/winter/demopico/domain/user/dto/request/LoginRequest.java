package _2024.winter.demopico.domain.user.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    public String username;
    public String password;
}
