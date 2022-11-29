package com.griddynamics.gridu.qa.user.report.user_management;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserManagementFeatures {

    public static final String UM_FEATURE_USER = "[FEATURE] user";

    public static final String UM_PBI_CREATE_USER = "[PBI] create new user";
    public static final String UM_TC_CREATE_USER_ERROR_ON_SAVING_PAYMENT =
            "[TC] create new user - create new user, error on saving payment";

}
