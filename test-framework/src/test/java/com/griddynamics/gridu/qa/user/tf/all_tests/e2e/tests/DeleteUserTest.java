package com.griddynamics.gridu.qa.user.tf.all_tests.e2e.tests;

import com.griddynamics.gridu.qa.user.*;
import com.griddynamics.gridu.qa.user.tf.all_tests.e2e.UserManagementE2EBaseTest;
import com.griddynamics.gridu.qa.user.tf.report.e2e.UserManagementE2EFeatures;
import com.griddynamics.gridu.qa.user.tf.steps.UserManagementSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static com.griddynamics.gridu.qa.user.tf.test_data.UserTestData.prepareBasicCreateUserRequestData;
import static com.griddynamics.gridu.qa.user.tf.test_data.UserTestData.prepareNotFoundUserDetails;
import static org.assertj.core.api.Assertions.assertThat;

@Feature(UserManagementE2EFeatures.FEATURE_USER)
@Story(UserManagementE2EFeatures.PBI_DELETE_USER)
public class DeleteUserTest extends UserManagementE2EBaseTest {

    @Test(description = UserManagementE2EFeatures.TC_DELETE_USER)
    @Description(UserManagementE2EFeatures.TC_DELETE_USER)
    public void deleteUser() {
        CreateUserRequest createUserRequest = prepareBasicCreateUserRequestData();
        CreateUserResponse createUserResponse = client.createUser(createUserRequest);
        UserManagementSteps.verifyBasicUserData(createUserRequest, createUserResponse);

        DeleteUserRequest deleteUserRequest = new DeleteUserRequest();
        deleteUserRequest.setUserId(createUserResponse.getUserDetails().getId());
        client.deleteUser(deleteUserRequest);

        GetUserDetailsRequest userDetailsRequest = new GetUserDetailsRequest();
        userDetailsRequest.setUserId(createUserResponse.getUserDetails().getId());
        GetUserDetailsResponse getUserDetailsResponse = client.getUserDetails(userDetailsRequest);
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
        UserManagementSteps.verifyBasicUsersDetails(deletedUserDetails, deletedUserDetailsTemplate);
        assertThat(deletedUserDetails.getAddresses())
                .as("Addresses list should be null")
                .isNull();
        assertThat(deletedUserDetails.getPayments())
                .as("Payments list should be null")
                .isNull();
    }

}
