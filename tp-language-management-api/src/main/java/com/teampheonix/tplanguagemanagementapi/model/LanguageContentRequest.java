package com.teampheonix.tplanguagemanagementapi.model;

import lombok.Data;

@Data
public class LanguageContentRequest {

    private Long postId;
    private String language;
    private String content;

}
