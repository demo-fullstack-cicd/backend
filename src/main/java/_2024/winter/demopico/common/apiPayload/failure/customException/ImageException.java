package _2024.winter.demopico.common.apiPayload.failure.customException;

public class ImageException {
    public static class ImageNotExistException extends RuntimeException{}
    public static class ImageDuplicateException extends RuntimeException{}
}
