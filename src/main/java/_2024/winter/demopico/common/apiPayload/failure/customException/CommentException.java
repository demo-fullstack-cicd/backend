package _2024.winter.demopico.common.apiPayload.failure.customException;

public class CommentException {
    public static class CommentNotExistException extends RuntimeException{}
    public static class CommentNotOwnershipException extends RuntimeException{}


}
