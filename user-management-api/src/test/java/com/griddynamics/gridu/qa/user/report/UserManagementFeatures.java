package com.griddynamics.gridu.qa.user.report;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserManagementFeatures {

    public static final String FEATURE_USER = "[FEATURE] user";

    public static final String PBI_CREATE_USER = "[PBI] create new user";
    public static final String TC_CREATE_USER = "[TC] create new user - create new user with new address and new " +
            "payment";

    public static final String PBI_GET_USER_DETAILS = "[PBI] user's details";
    public static final String TC_GET_USERS_DETAILS = "[TC] user's details - get user's details";

    public static final String PBI_UPDATE_USER = "[PBI] update user";
    public static final String TC_UPDATE_USER = "[TC] update user - update user basic data";

}
