package wang.diff.scaffold.dto;

import lombok.Data;

@Data
public class SendVerificationCodeDTO {
    private String mobile;
    private String code;
}
