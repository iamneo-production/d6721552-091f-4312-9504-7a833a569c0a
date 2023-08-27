package com.teamphoenix.tpauthapi.client;

import com.teamphoenix.tpauthapi.config.FeignClientConfig;
import com.teamphoenix.tpauthapi.model.AuthRequest;
import com.teamphoenix.tpauthapi.model.UserClaims;
import feign.Headers;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.teamphoenix.tpauthapi.model.ResponseDto;

@Component
@FeignClient(name = "tp-user-profile-api",
            configuration = FeignClientConfig.class,
            fallbackFactory = UserProfileClientApiFallbackFactory.class)
public interface UserProfileClientApi {

    @GetMapping("/api/user-profile/user/validate")
    @Headers({
        "API_KEY : dHAtdXNlci1wcm9maWxlLWFwaQ==",
        "USER_ID : {userID}",
        "CLAIMS : AUTH"
    })
    ResponseDto<UserClaims> validateUser(AuthRequest authRequest, @Param("userID") String userID);
    
}
