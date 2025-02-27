package _2024.winter.demopico.common.apiPayload.failure.customException;

public class UserException {
    public static class UsernameDuplicateException extends RuntimeException{}
    public static class EmailDuplicateException extends RuntimeException{}
    public static class UsernameNotExistException extends RuntimeException{}
    public static class PasswordNotValidException extends RuntimeException{}
    public static class RefreshTokenNotValidException extends RuntimeException{}
    public static class UserNotClubMemberException extends RuntimeException{}
}
