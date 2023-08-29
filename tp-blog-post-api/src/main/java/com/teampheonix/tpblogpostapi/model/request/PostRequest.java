package com.teampheonix.tpblogpostapi.model.request;

import lombok.Data;

@Data
public class PostRequest {

    private String postName;
    private LanguageContentRequest content;

}
