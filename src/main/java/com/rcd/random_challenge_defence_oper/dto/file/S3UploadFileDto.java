package com.rcd.random_challenge_defence_oper.dto.file;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class S3UploadFileDto {

    private Long id;
    private String key;
    private String url;

}
