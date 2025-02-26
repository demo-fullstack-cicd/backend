package _2024.winter.demopico.common.apiPayload.failure.customException;

public class ReplyException {
    public static class ReplyNotExistException extends RuntimeException{}
    public static class ReplyNotOwnershipException extends RuntimeException{}
}
