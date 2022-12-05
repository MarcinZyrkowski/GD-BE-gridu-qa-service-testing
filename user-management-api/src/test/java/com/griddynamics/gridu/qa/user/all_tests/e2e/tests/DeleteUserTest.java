package com.griddynamics.gridu.qa.user.all_tests.e2e.tests;

import com.griddynamics.gridu.qa.user.*;
import com.griddynamics.gridu.qa.user.all_tests.e2e.UserManagementE2EBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static com.griddynamics.gridu.qa.user.all_tests.test_data.UserTestData.prepareBasicCreateUserRequestData;
import static com.griddynamics.gridu.qa.user.all_tests.test_data.UserTestData.prepareNotFoundUserDetails;
import static com.griddynamics.gridu.qa.user.report.e2e.UserManagementE2EFeatures.*;
import static com.griddynamics.gridu.qa.user.steps.UserManagementSteps.verifyBasicUserData;
import static com.griddynamics.gridu.qa.user.steps.UserManagementSteps.verifyBasicUsersDetails;
import static org.assertj.core.api.Assertions.assertThat;

@Feature(FEATURE_USER)
@Story(PBI_DELETE_USER)
public class DeleteUserTest extends UserManagementE2EBaseTest {

    @Test(description = TC_DELETE_USER)
    @Description(TC_DELETE_USER)
    public void deleteUser() {
        CreateUserRequest createUserRequest = prepareBasicCreateUserRequestData();
        CreateUserResponse createUserResponse = userManagementClient.createUser(createUserRequest);
        verifyBasicUserData(createUserRequest, createUserResponse);

        DeleteUserRequest deleteUserRequest = new DeleteUserRequest();
        deleteUserRequest.setUserId(createUserResponse.getUserDetails().getId());
        userManagementClient.deleteUser(deleteUserRequest);

        GetUserDetailsRequest userDetailsRequest = new GetUserDetailsRequest();
        userDetailsRequest.setUserId(createUserResponse.getUserDetails().getId());
        GetUserDetailsResponse getUserDetailsResponse = userManagementClient.getUserDetails(userDetailsRequest);
        assertThat(getUserDetailsResponse)
                .as("Get User details response should not be null")
                .isNotNull();
        assertThat(getUserDetailsResponse.getUserDetails())
                .as("User details should not be null")
                .isNotNull();
        UserDetails deletedUserDetails = getUserDetailsResponse.getUserDetails();

        UserDetails deletedUserDetailsTemplate =
                prepareNotFoundUserDetails(createUserResponse.getUserDetails().getId());
        assertThat(deletedUserDetails.getId())
                .as("Deleted user's details id and deleted user's id template should be equal")
                .isEqualTo(deletedUserDetailsTemplate.getId());
        verifyBasicUsersDetails(deletedUserDetails, deletedUserDetailsTemplate);
        assertThat(deletedUserDetails.getAddresses())
                .as("Addresses list should be null")
                .isNull();
        assertThat(deletedUserDetails.getPayments())
                .as("Payments list should be null")
                .isNull();
    }

}
