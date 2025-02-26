package _2024.winter.demopico.domain.user.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
public class SignupRequest {
    public String username;
    public String password;
    public String email;

    @Builder
    public SignupRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
