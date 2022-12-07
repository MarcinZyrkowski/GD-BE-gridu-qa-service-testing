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
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static com.griddynamics.gridu.qa.user.tf.test_data.UserTestData.prepareBasicCreateUserRequestData;

@Feature(UserManagementE2EFeatures.FEATURE_USER)
@Story(UserManagementE2EFeatures.PBI_GET_USER_DETAILS)
public class GetUserDetailsTest extends UserManagementE2EBaseTest {

    @Test(description = UserManagementE2EFeatures.TC_GET_USERS_DETAILS)
    @Description(UserManagementE2EFeatures.TC_GET_USERS_DETAILS)
    public void getUserDetails() {
        CreateUserRequest createUserRequest = prepareBasicCreateUserRequestData();
        CreateUserResponse createUserResponse = client.createUser(createUserRequest);
        UserManagementSteps.verifyBasicUserData(createUserRequest, createUserResponse);

        GetUserDetailsRequest userDetailsRequest = new GetUserDetailsRequest();
        userDetailsRequest.setUserId(createUserResponse.getUserDetails().getId());
        GetUserDetailsResponse getUserDetailsResponse = client.getUserDetails(userDetailsRequest);

        // bug with xml gregorian calendar
        UserManagementSteps.verifyCreatedUserDetails(createUserResponse, getUserDetailsResponse);
    }

}
