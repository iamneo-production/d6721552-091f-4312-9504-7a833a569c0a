package com.teampheonix.tpblogpostapi.model.request;

import lombok.Data;

@Data
public class LanguageContentRequest {

    private Long postId;
    private String language;
    private String content;

}
