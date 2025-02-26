package _2024.winter.demopico.common.apiPayload.success;

import _2024.winter.demopico.common.apiPayload.BaseApiResponse;
import _2024.winter.demopico.domain.comment.dto.response.DeleteCommentResponse;
import _2024.winter.demopico.domain.comment.dto.response.GetCommentsResponse;
import _2024.winter.demopico.domain.comment.dto.response.UpdateCommentResponse;
import _2024.winter.demopico.domain.comment.dto.response.UploadCommentResponse;
import _2024.winter.demopico.domain.reply.dto.response.DeleteReplyResponse;
import _2024.winter.demopico.domain.reply.dto.response.UpdateReplyResponse;
import _2024.winter.demopico.domain.reply.dto.response.UploadReplyResponse;
import _2024.winter.demopico.domain.feed.dto.response.*;
import _2024.winter.demopico.domain.image.dto.response.GetImagesResponse;
import _2024.winter.demopico.domain.image.dto.response.GetPresignedUrlToDownloadResponse;
import _2024.winter.demopico.domain.image.dto.response.GetPresignedUrlToUploadResponse;
import _2024.winter.demopico.domain.image.dto.response.UploadImageResponse;
import _2024.winter.demopico.domain.user.dto.response.*;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SuccessApiResponse <T> extends BaseApiResponse {
    private final T response;

    public SuccessApiResponse(Boolean isSuccess, String code, String message, T response) {
        super(isSuccess, code, message);
        this.response = response;
    }

    // [register]
    public static SuccessApiResponse<CheckUsernameDuplicateResponse> onSuccessCheckUsernameDuplicate(CheckUsernameDuplicateResponse response){
        return new SuccessApiResponse<>(true, HttpStatus.OK.toString()
                , "사용자이름 중복확인 성공", response);
    }
    public static SuccessApiResponse<SendAuthEmailResponse> onSuccessSendAuthEmail(SendAuthEmailResponse response){
        return new SuccessApiResponse<>(true, HttpStatus.OK.toString()
                , "이메일 전송 성공", response);
    }
    public static SuccessApiResponse<CheckAuthEmailResponse> onSuccessCheckAuthEmail(CheckAuthEmailResponse response){
        return new SuccessApiResponse<>(true, HttpStatus.OK.toString()
                , "이메일 인증 성공", response);
    }
    public static SuccessApiResponse<SignupResponse> onSuccessSignup(SignupResponse response){
        return new SuccessApiResponse<>(true, HttpStatus.CREATED.toString()
                , "회원가입 성공", response);
    }

    // [login]
    public static SuccessApiResponse<LoginResponse> onSuccessLogin(LoginResponse response){
        return new SuccessApiResponse<>(true, HttpStatus.OK.toString()
                , "로그인 성공", response);
    }

    // [reissue]
    public static SuccessApiResponse<ReissueResponse> onSuccessReissue(ReissueResponse response){
        return new SuccessApiResponse<>(true, HttpStatus.CREATED.toString()
                , "토큰 재발급 성공", response);
    }

    // [IMAGE]
    public static SuccessApiResponse<GetPresignedUrlToUploadResponse> onSuccessGetPresignedUrlToUpload(GetPresignedUrlToUploadResponse response){
        return new SuccessApiResponse<>(true, HttpStatus.OK.toString()
                , "이미지 업로드용 URL 발급 성공", response);
    }
    public static SuccessApiResponse<UploadImageResponse> onSuccessUploadImage(UploadImageResponse response){
        return new SuccessApiResponse<>(true, HttpStatus.OK.toString()
                , "이미지 업로드 성공", response);
    }

    public static SuccessApiResponse<GetPresignedUrlToDownloadResponse> onSuccessGetPresignedUrlToDownload(GetPresignedUrlToDownloadResponse response){
        return new SuccessApiResponse<>(true, HttpStatus.OK.toString()
                , "이미지 다운로드용 URL 발급 성공", response);
    }

    public static SuccessApiResponse<GetImagesResponse> onSuccessGetImages(GetImagesResponse response){
        return new SuccessApiResponse<>(true, HttpStatus.OK.toString()
                , "모든 이미지 조회 성공", response);
    }

    // [FEED]
    public static SuccessApiResponse<GetFeedsResponse> onSuccessGetFeeds(GetFeedsResponse response){
        return new SuccessApiResponse<>(true, HttpStatus.OK.toString()
                , "페이징된 게시글 조회 성공", response);
    }
    public static SuccessApiResponse<UploadOneFeedResponse> onSuccessUploadOneFeed(UploadOneFeedResponse response){
        return new SuccessApiResponse<>(true, HttpStatus.OK.toString()
                , "게시글 업로드 성공", response);
    }

    public static SuccessApiResponse<GetOneFeedResponse> onSuccessGetOneFeed(GetOneFeedResponse response){
        return new SuccessApiResponse<>(true, HttpStatus.OK.toString()
                , "게시글 세부 조회 성공", response);
    }

    public static SuccessApiResponse<DeleteOneFeedResponse> onSuccessDeleteOneFeed(DeleteOneFeedResponse response){
        return new SuccessApiResponse<>(true, HttpStatus.OK.toString()
                , "게시글 삭제 성공", response);
    }

    public static SuccessApiResponse<UpdateOneFeedResponse> onSuccessUpdateOneFeed(UpdateOneFeedResponse response){
        return new SuccessApiResponse<>(true, HttpStatus.OK.toString()
                , "게시글 수정 성공", response);
    }

    // [COMMENT]
    public static SuccessApiResponse<UploadCommentResponse> onSuccessUploadComment(UploadCommentResponse response){
        return new SuccessApiResponse<>(true, HttpStatus.OK.toString()
                , "댓글 작성 성공", response);
    }
    public static SuccessApiResponse<UpdateCommentResponse> onSuccessUpdateComment(UpdateCommentResponse response){
        return new SuccessApiResponse<>(true, HttpStatus.OK.toString()
                , "댓글 수정 성공", response);
    }
    public static SuccessApiResponse<DeleteCommentResponse> onSuccessDeleteComment(DeleteCommentResponse response){
        return new SuccessApiResponse<>(true, HttpStatus.OK.toString()
                , "댓글 삭제 성공", response);
    }
    public static SuccessApiResponse<GetCommentsResponse> onSuccessGetComments(GetCommentsResponse response){
        return new SuccessApiResponse<>(true, HttpStatus.OK.toString()
                , "댓글 조회 성공", response);
    }

    // [REPLY]
    public static SuccessApiResponse<UploadReplyResponse> onSuccessUploadReply(UploadReplyResponse response){
        return new SuccessApiResponse<>(true, HttpStatus.OK.toString()
                , "대댓글 작성 성공", response);
    }
    public static SuccessApiResponse<UpdateReplyResponse> onSuccessUpdateReply(UpdateReplyResponse response){
        return new SuccessApiResponse<>(true, HttpStatus.OK.toString()
                , "대댓글 수정 성공", response);
    }
    public static SuccessApiResponse<DeleteReplyResponse> onSuccessDeleteReply(DeleteReplyResponse response){
        return new SuccessApiResponse<>(true, HttpStatus.OK.toString()
                , "대댓글 삭제 성공", response);
    }

}
