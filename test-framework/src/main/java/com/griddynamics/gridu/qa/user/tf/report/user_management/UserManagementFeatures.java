package com.griddynamics.gridu.qa.user.tf.report.user_management;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserManagementFeatures {

    public static final String UM_FEATURE_USER = "[FEATURE] user";

    public static final String UM_PBI_CREATE_USER = "[PBI] create new user";
    public static final String UM_TC_CREATE_USER_ERROR_ON_SAVING_PAYMENT =
            "[TC] create new user - create new user, error on saving payment";
    public static final String UM_TC_CREATE_USER_ERROR_ON_SAVING_ADDRESS =
            "[TC] create new user - create new user, error on saving address";
    public static final String UM_TC_CREATE_USER_WITH_VALID_PAYMENT_AND_ADDRESS =
            "[TC] create new user - create new user, error on saving payment";

}