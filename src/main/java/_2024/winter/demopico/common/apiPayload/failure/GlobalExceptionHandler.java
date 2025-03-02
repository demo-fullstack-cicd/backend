package _2024.winter.demopico.common.apiPayload.failure;


import _2024.winter.demopico.common.apiPayload.failure.customException.*;
import _2024.winter.demopico.common.apiPayload.failure.customExceptionStatus.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // [USER]
    @ExceptionHandler(UserException.UsernameDuplicateException.class)
    public ResponseEntity<ExceptionApiResponse> handleException(UserException.UsernameDuplicateException e){
        log.error("[GlobalExceptionHandler] UserException.UsernameDuplicateException occurred");
        return ResponseEntity
                .status(
                        UserExceptionStatus.USERNAME_DUPLICATE.getHttpStatus()
                )
                .body(
                        new ExceptionApiResponse(
                        false, UserExceptionStatus.USERNAME_DUPLICATE.getCode(), UserExceptionStatus.USERNAME_DUPLICATE.getMessage()
                        )
                );
    }

    @ExceptionHandler(UserException.EmailDuplicateException.class)
    public ResponseEntity<ExceptionApiResponse> handleException(UserException.EmailDuplicateException e){
        log.error("[GlobalExceptionHandler] UserException.EmailDuplicateException occurred");
        return ResponseEntity
                .status(
                        UserExceptionStatus.EMAIL_DUPLICATE.getHttpStatus()
                )
                .body(
                        new ExceptionApiResponse(
                                false, UserExceptionStatus.EMAIL_DUPLICATE.getCode(), UserExceptionStatus.EMAIL_DUPLICATE.getMessage()
                        )
                );
    }

    @ExceptionHandler(UserException.UsernameNotExistException.class)
    public ResponseEntity<ExceptionApiResponse> handleException(UserException.UsernameNotExistException e){
        log.error("[GlobalExceptionHandler] UserException.UsernameNotExistException occurred");
        return ResponseEntity
                .status(
                        UserExceptionStatus.USERNAME_NOT_EXIST.getHttpStatus()
                )
                .body(
                        new ExceptionApiResponse(
                                false, UserExceptionStatus.USERNAME_NOT_EXIST.getCode(), UserExceptionStatus.USERNAME_NOT_EXIST.getMessage()
                        )
                );
    }

    @ExceptionHandler(UserException.PasswordNotValidException.class)
    public ResponseEntity<ExceptionApiResponse> handleException(UserException.PasswordNotValidException e){
        log.error("[GlobalExceptionHandler] UserException.PasswordNotValidException occurred");
        return ResponseEntity
                .status(
                        UserExceptionStatus.PASSWORD_NOT_VALID.getHttpStatus()
                )
                .body(
                        new ExceptionApiResponse(
                                false, UserExceptionStatus.PASSWORD_NOT_VALID.getCode(), UserExceptionStatus.PASSWORD_NOT_VALID.getMessage()
                        )
                );
    }

    @ExceptionHandler(UserException.RefreshTokenNotValidException.class)
    public ResponseEntity<ExceptionApiResponse> handleException(UserException.RefreshTokenNotValidException e){
        log.error("[GlobalExceptionHandler] UserException.RefreshTokenNotValidException occurred");
        return ResponseEntity
                .status(
                        UserExceptionStatus.REFRESH_TOKEN_NOT_VALID.getHttpStatus()
                )
                .body(
                        new ExceptionApiResponse(
                                false, UserExceptionStatus.REFRESH_TOKEN_NOT_VALID.getCode(), UserExceptionStatus.REFRESH_TOKEN_NOT_VALID.getMessage()
                        )
                );
    }

    @ExceptionHandler(UserException.UserNotClubMemberException.class)
    public ResponseEntity<ExceptionApiResponse> handleException(UserException.UserNotClubMemberException e){
        log.error("[GlobalExceptionHandler] UserException.UserNotClubMemberException occurred");
        return ResponseEntity
                .status(
                        UserExceptionStatus.USER_NOT_CLUB_MEMBER.getHttpStatus()
                )
                .body(
                        new ExceptionApiResponse(
                                false, UserExceptionStatus.USER_NOT_CLUB_MEMBER.getCode(), UserExceptionStatus.USER_NOT_CLUB_MEMBER.getMessage()
                        )
                );
    }

    // [IMAGE]
    @ExceptionHandler(ImageException.ImageNotExistException.class)
    public ResponseEntity<ExceptionApiResponse> handleException(ImageException.ImageNotExistException e){
        log.error("[GlobalExceptionHandler] ImageException.ImageNotExistException occurred");
        return ResponseEntity
                .status(
                        ImageExceptionStatus.IMAGE_NOT_EXIST.getHttpStatus()
                )
                .body(
                        new ExceptionApiResponse(
                                false, ImageExceptionStatus.IMAGE_NOT_EXIST.getCode(), ImageExceptionStatus.IMAGE_NOT_EXIST.getMessage()
                        )
                );
    }

    @ExceptionHandler(ImageException.ImageDuplicateException.class)
    public ResponseEntity<ExceptionApiResponse> handleException(ImageException.ImageDuplicateException e){
        log.error("[GlobalExceptionHandler] ImageException.ImageDuplicateException occurred");
        return ResponseEntity
                .status(
                        ImageExceptionStatus.IMAGE_DUPLICATE.getHttpStatus()
                )
                .body(
                        new ExceptionApiResponse(
                                false, ImageExceptionStatus.IMAGE_DUPLICATE.getCode(), ImageExceptionStatus.IMAGE_DUPLICATE.getMessage()
                        )
                );
    }

    // [FEED]
    @ExceptionHandler(FeedException.FeedNotExistException.class)
    public ResponseEntity<ExceptionApiResponse> handleException(FeedException.FeedNotExistException e){
        log.error("[GlobalExceptionHandler] FeedException.FeedNotExistException occurred");
        return ResponseEntity
                .status(
                        FeedExceptionStatus.FEED_NOT_EXIST.getHttpStatus()
                )
                .body(
                        new ExceptionApiResponse(
                                false, FeedExceptionStatus.FEED_NOT_EXIST.getCode(), FeedExceptionStatus.FEED_NOT_EXIST.getMessage()
                        )
                );
    }

    // [COMMENT]
    @ExceptionHandler(CommentException.CommentNotExistException.class)
    public ResponseEntity<ExceptionApiResponse> handleException(CommentException.CommentNotExistException e){
        log.error("[GlobalExceptionHandler] CommentException.CommentNotExistException occurred");
        return ResponseEntity
                .status(
                        CommentExceptionStatus.COMMENT_NOT_EXIST.getHttpStatus()
                )
                .body(
                        new ExceptionApiResponse(
                                false, CommentExceptionStatus.COMMENT_NOT_EXIST.getCode(), CommentExceptionStatus.COMMENT_NOT_EXIST.getMessage()
                        )
                );
    }

    @ExceptionHandler(CommentException.CommentNotOwnershipException.class)
    public ResponseEntity<ExceptionApiResponse> handleException(CommentException.CommentNotOwnershipException e){
        log.error("[GlobalExceptionHandler] CommentException.CommentNotOwnershipException occurred");
        return ResponseEntity
                .status(
                        CommentExceptionStatus.COMMENT_NOT_OWNERSHIP.getHttpStatus()
                )
                .body(
                        new ExceptionApiResponse(
                                false, CommentExceptionStatus.COMMENT_NOT_OWNERSHIP.getCode(), CommentExceptionStatus.COMMENT_NOT_OWNERSHIP.getMessage()
                        )
                );
    }

    // [REPLY]
    @ExceptionHandler(ReplyException.ReplyNotExistException.class)
    public ResponseEntity<ExceptionApiResponse> handleException(ReplyException.ReplyNotExistException e){
        log.error("[GlobalExceptionHandler] ReplyException.ReplyNotExistException occurred");
        return ResponseEntity
                .status(
                        ReplyExceptionStatus.REPLY_NOT_EXIST.getHttpStatus()
                )
                .body(
                        new ExceptionApiResponse(
                                false, ReplyExceptionStatus.REPLY_NOT_EXIST.getCode(), ReplyExceptionStatus.REPLY_NOT_EXIST.getMessage()
                        )
                );
    }

    @ExceptionHandler(ReplyException.ReplyNotOwnershipException.class)
    public ResponseEntity<ExceptionApiResponse> handleException(ReplyException.ReplyNotOwnershipException e){
        log.error("[GlobalExceptionHandler] ReplyException.ReplyNotOwnershipException occurred");
        return ResponseEntity
                .status(
                        ReplyExceptionStatus.REPLY_NOT_OWNERSHIP.getHttpStatus()
                )
                .body(
                        new ExceptionApiResponse(
                                false, ReplyExceptionStatus.REPLY_NOT_OWNERSHIP.getCode(), ReplyExceptionStatus.REPLY_NOT_OWNERSHIP.getMessage()
                        )
                );
    }

    // [STUDY]
    @ExceptionHandler(StudyException.StudyNotExistException.class)
    public ResponseEntity<ExceptionApiResponse> handleException(StudyException.StudyNotExistException e){
        log.error("[GlobalExceptionHandler] StudyException.StudyNotExistException occurred");
        return ResponseEntity
                .status(
                        StudyExceptionStatus.STUDY_NOT_EXIST.getHttpStatus()
                )
                .body(
                        new ExceptionApiResponse(
                                false, StudyExceptionStatus.STUDY_NOT_EXIST.getCode(), StudyExceptionStatus.STUDY_NOT_EXIST.getMessage()
                        )
                );
    }

    @ExceptionHandler(StudyException.StudyNotOwnerException.class)
    public ResponseEntity<ExceptionApiResponse> handleException(StudyException.StudyNotOwnerException e){
        log.error("[GlobalExceptionHandler] StudyException.StudyNotOwnerException occurred");
        return ResponseEntity
                .status(
                        StudyExceptionStatus.STUDY_NOT_OWNER.getHttpStatus()
                )
                .body(
                        new ExceptionApiResponse(
                                false, StudyExceptionStatus.STUDY_NOT_OWNER.getCode(), StudyExceptionStatus.STUDY_NOT_OWNER.getMessage()
                        )
                );
    }
}