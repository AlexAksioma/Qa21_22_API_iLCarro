package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ErrorMessageDto {
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
