package com.teamphoenix.tpauthapi.client;

import com.teamphoenix.tpauthapi.exception.ApiErrorCodes;
import com.teamphoenix.tpauthapi.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserProfileClientApiFallbackFactory implements FallbackFactory<UserProfileClientApi>  {

    @Override
    public UserProfileClientApi create(Throwable cause) {
        return authRequest -> {
            if (cause instanceof ApiException) {
                throw new ApiException(((ApiException) cause).getCode());
            }
            log.error("Error occurred while calling User Profile API - Cause", cause);
            throw new ApiException(ApiErrorCodes.USER_PROFILE_API_ERROR);
        };
    }

}
