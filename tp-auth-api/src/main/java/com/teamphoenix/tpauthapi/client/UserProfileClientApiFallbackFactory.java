package com.teamphoenix.tpauthapi.client;

import com.teamphoenix.tpauthapi.exception.TpErrorCodes;
import com.teamphoenix.tpauthapi.exception.TpException;
import com.teamphoenix.tpauthapi.model.AuthRequest;
import com.teamphoenix.tpauthapi.model.ResponseDto;
import com.teamphoenix.tpauthapi.model.UserClaims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserProfileClientApiFallbackFactory implements FallbackFactory<UserProfileClientApi>  {

    @Override
    public UserProfileClientApi create(Throwable cause) {
        return new UserProfileClientApi() {
            @Override
            public ResponseDto<UserClaims> validateUser(AuthRequest authRequest, String userID) {
                log.error("Error occurred while calling User Profile API");
                throw new TpException(TpErrorCodes.USER_PROFILE_API_ERROR);
            }
        };
    }

}
