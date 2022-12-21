package com.griddynamics.gridu.qa.user.tf.all_tests.e2e.tests;

import com.griddynamics.gridu.qa.user.CreateUserRequest;
import com.griddynamics.gridu.qa.user.CreateUserResponse;
import com.griddynamics.gridu.qa.user.GetUserDetailsRequest;
import com.griddynamics.gridu.qa.user.GetUserDetailsResponse;
import com.griddynamics.gridu.qa.user.tf.all_tests.e2e.UserManagementE2EBaseTest;
import com.griddynamics.gridu.qa.user.tf.report.e2e.UserManagementE2EFeatures;
import com.griddynamics.gridu.qa.user.tf.steps.UserManagementSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static com.griddynamics.gridu.qa.user.tf.report.e2e.UserManagementE2EBugs.BUG_GREGORIAN_CALENDAR;
import static com.griddynamics.gridu.qa.user.tf.report.e2e.UserManagementE2EFeatures.*;
import static com.griddynamics.gridu.qa.user.tf.test_data.UserTestData.prepareBasicCreateUserRequestData;

@Feature(FEATURE_USER)
@Story(PBI_GET_USER_DETAILS)
public class GetUserDetailsTest extends UserManagementE2EBaseTest {

    @Test(description = TC_GET_USERS_DETAILS)
    @Description(TC_GET_USERS_DETAILS)
    @Issue(BUG_GREGORIAN_CALENDAR)
    public void getUserDetails() {
        // given
        CreateUserRequest createUserRequest = prepareBasicCreateUserRequestData();
        CreateUserResponse createUserResponse = client.createUser(createUserRequest);
        UserManagementSteps.verifyBasicUserData(createUserRequest, createUserResponse);

        // when
        GetUserDetailsRequest userDetailsRequest = new GetUserDetailsRequest();
        userDetailsRequest.setUserId(createUserResponse.getUserDetails().getId());
        GetUserDetailsResponse getUserDetailsResponse = client.getUserDetails(userDetailsRequest);

        // then
        // bug with xml gregorian calendar
        UserManagementSteps.verifyCreatedUserDetails(createUserResponse, getUserDetailsResponse);
    }

}
