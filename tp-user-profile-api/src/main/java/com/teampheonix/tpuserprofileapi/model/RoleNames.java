package com.teampheonix.tpuserprofileapi.model;

import com.teampheonix.tpuserprofileapi.exception.TpErrorCodes;
import com.teampheonix.tpuserprofileapi.exception.TpException;

public enum RoleNames {

    ADMIN,
    BLOGGER,
    READER,
    CONTENT_MODERATOR,
    TRANSLATOR;

	public static RoleNames valueOfIgnoreCase(String value) {
        for (RoleNames roles : RoleNames.values()) {
            if (roles.name().equalsIgnoreCase(value)) {
                return roles;
            }
        }
        throw new TpException(TpErrorCodes.INVALID_ROLE);
    }
	
}
