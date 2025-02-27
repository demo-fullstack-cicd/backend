package _2024.winter.demopico.domain.user.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
public class SignupRequest {
    public String username;
    public String password;
    public String email;
    public String name;
    public String studentId;
    public String phone;

    @Builder
    public SignupRequest(String username, String password, String email, String name, String studentId, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.studentId = studentId;
        this.phone = phone;
    }
}
