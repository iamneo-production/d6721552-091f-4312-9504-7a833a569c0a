package com.teampheonix.tpuserprofileapi.model;



import com.teampheonix.tpuserprofileapi.exception.InvalidRequestException;

public enum RoleNames {
	ADMINISTRATOR,BLOGGER,READER,CONTENTMODERATOR,TRANSLATOR ;

	public static RoleNames valueOfIgnoreCase(String value) {
        for (RoleNames roles : RoleNames.values()) {
            if (roles.name().equalsIgnoreCase(value)) {
                return roles;
            }
        }
        throw new InvalidRequestException("No enum constant with name " + value);
    }
	
}
