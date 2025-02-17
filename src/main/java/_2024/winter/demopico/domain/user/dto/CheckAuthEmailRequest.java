package _2024.winter.demopico.domain.user.dto;

import lombok.Data;

@Data
public class CheckAuthEmailRequest {
    public String email;
    public String verificationCode;
}
