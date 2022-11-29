package com.griddynamics.gridu.qa.user.tests.e2e;

import com.griddynamics.gridu.qa.user.CreateUserRequest;
import com.griddynamics.gridu.qa.user.CreateUserResponse;
import com.griddynamics.gridu.qa.user.GetUserDetailsRequest;
import com.griddynamics.gridu.qa.user.GetUserDetailsResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static com.griddynamics.gridu.qa.user.report.e2e.UserManagementE2EFeatures.*;
import static com.griddynamics.gridu.qa.user.steps.UserManagementSteps.verifyBasicUserData;
import static com.griddynamics.gridu.qa.user.steps.UserManagementSteps.verifyCreatedUserDetails;
import static com.griddynamics.gridu.qa.user.tests.test_data.UserTestData.prepareBasicCreateUserRequestData;

@Feature(FEATURE_USER)
@Story(PBI_GET_USER_DETAILS)
public class GetUserDetailsTest extends UserManagementE2EBaseTest {

    @Test(description = TC_GET_USERS_DETAILS)
    @Description(TC_GET_USERS_DETAILS)
    public void getUserDetails() {
        CreateUserRequest createUserRequest = prepareBasicCreateUserRequestData();
        CreateUserResponse createUserResponse = userManagementClient.createUser(createUserRequest);
        verifyBasicUserData(createUserRequest, createUserResponse);

        GetUserDetailsRequest userDetailsRequest = new GetUserDetailsRequest();
        userDetailsRequest.setUserId(createUserResponse.getUserDetails().getId());
        GetUserDetailsResponse getUserDetailsResponse = userManagementClient.getUserDetails(userDetailsRequest);

        // bug with xml gregorian calendar
        verifyCreatedUserDetails(createUserResponse, getUserDetailsResponse);
    }

}
