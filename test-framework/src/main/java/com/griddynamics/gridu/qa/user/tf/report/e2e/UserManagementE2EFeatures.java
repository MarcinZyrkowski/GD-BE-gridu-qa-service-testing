package com.griddynamics.gridu.qa.user.tf.report.e2e;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserManagementE2EFeatures {

    public static final String FEATURE_USER = "[FEATURE] user";

    public static final String PBI_CREATE_USER = "[PBI] create new user";
    public static final String TC_CREATE_USER_WITH_ADDRESS_AND_PAYMENT =
            "[TC] create new user - create new user with new address and new payment";
    public static final String TC_CREATE_USER_WITH_PAYMENT = "[TC] create new user - create new user with new " +
            "payment";
    public static final String TC_CREATE_USER_WITH_ADDRESS = "[TC] create new user - create new user with new " +
            "address";
    public static final String TC_CREATE_USER_WITH_BASIC_DATA = "[TC] create new user - create new user with basic " +
            "data";

    public static final String PBI_GET_USER_DETAILS = "[PBI] user's details";
    public static final String TC_GET_USERS_DETAILS = "[TC] user's details - get user's details";

    public static final String PBI_UPDATE_USER = "[PBI] update user";
    public static final String TC_UPDATE_USER = "[TC] update user - update user's basic data";

    public static final String PBI_DELETE_USER = "[PBI] delete user";
    public static final String TC_DELETE_USER = "[TC] delete user";

}
