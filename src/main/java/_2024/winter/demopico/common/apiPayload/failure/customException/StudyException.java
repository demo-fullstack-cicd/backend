package _2024.winter.demopico.common.apiPayload.failure.customException;

public class StudyException {
    public static class StudyNotExistException extends RuntimeException{}
    public static class StudyNotOwnerException extends RuntimeException{}
}
