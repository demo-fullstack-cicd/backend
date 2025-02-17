package _2024.winter.demopico.domain.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class RegisterRequest {
    public String username;
    public String password;
    public String email;

    @Builder
    public RegisterRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
