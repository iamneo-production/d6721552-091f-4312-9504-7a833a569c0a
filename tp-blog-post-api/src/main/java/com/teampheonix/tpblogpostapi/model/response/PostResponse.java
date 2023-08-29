package com.teampheonix.tpblogpostapi.model.response;

import com.teampheonix.tpblogpostapi.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

    private Post post;
    private List<LanguageContentResponse> contents;

}
