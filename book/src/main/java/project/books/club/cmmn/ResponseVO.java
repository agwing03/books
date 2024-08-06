package project.books.club.cmmn;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseVO<T> {
    private HttpStatus statusCode;
    private String resultMsg;
    private T resultData;

    public ResponseVO(final HttpStatus statusCode, final String resultMsg) {
        this.statusCode = statusCode;
        this.resultMsg = resultMsg;
        this.resultData = null;
    }

    public static<T> ResponseVO<T> res(
    		final HttpStatus statusCode, 
    		final String resultMsg
    	) {
        return res(statusCode, resultMsg, null);
    }

    public static<T> ResponseVO<T> res(
    		final HttpStatus statusCode, 
    		final String resultMsg, 
    		final T t
    	) {
        return ResponseVO.<T>builder()
                .statusCode(statusCode)
                .resultMsg(resultMsg)
                .resultData(t)
                .build();
    }
}
