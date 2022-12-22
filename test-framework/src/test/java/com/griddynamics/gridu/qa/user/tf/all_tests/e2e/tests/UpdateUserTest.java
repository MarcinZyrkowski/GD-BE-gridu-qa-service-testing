package com.griddynamics.gridu.qa.user.tf.all_tests.e2e.tests;

import com.griddynamics.gridu.qa.user.*;
import com.griddynamics.gridu.qa.user.tf.all_tests.e2e.UserManagementE2EBaseTest;
import com.griddynamics.gridu.qa.user.tf.steps.GeneralSteps;
import com.griddynamics.gridu.qa.user.tf.test_data.data_provides.UserDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lombok.SneakyThrows;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static com.griddynamics.gridu.qa.user.tf.report.e2e.UserManagementE2EFeatures.*;
import static com.griddynamics.gridu.qa.user.tf.steps.GeneralSteps.verifyAllUsersUpdatedData;
import static com.griddynamics.gridu.qa.user.tf.test_data.UserTestData.prepareBasicCreateUserRequestData;
import static com.griddynamics.gridu.qa.user.tf.test_data.UserTestData.prepareOtherUserDetails;

@Feature(FEATURE_USER)
@Story(PBI_UPDATE_USER)
public class UpdateUserTest extends UserManagementE2EBaseTest {

    @Test(description = TC_UPDATE_USER)
    @Description(TC_UPDATE_USER)
    @SneakyThrows
    public void updateBasicUsersData() {
        // given
        CreateUserRequest createUserRequest = prepareBasicCreateUserRequestData();
        CreateUserResponse createUserResponse = client.createUser(createUserRequest);
        GeneralSteps.verifyAllUsersData(createUserRequest, createUserResponse);

        // when
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        UserDetails userDetailsToUpdate = prepareOtherUserDetails();
        userDetailsToUpdate.setId(createUserResponse.getUserDetails().getId());
        updateUserRequest.setUserDetails(userDetailsToUpdate);

        // then
        UpdateUserResponse updateUserResponse = client.updateUser(updateUserRequest);
        verifyAllUsersUpdatedData(updateUserRequest, updateUserResponse);
    }

    @Test(description = TC_UPDATE_USER_PAYMENTS, dataProviderClass = UserDataProvider.class,
            dataProvider = "validUserWithPayments")
    @Description(TC_UPDATE_USER_PAYMENTS)
    @SneakyThrows
    public void updateUsersPayments(CreateUserRequest createUserRequest) {
        // given
        CreateUserResponse createUserResponse = client.createUser(createUserRequest);
        GeneralSteps.verifyAllUsersData(createUserRequest, createUserResponse);

        // when
        // updated basic user's details
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        UserDetails userDetailsToUpdate = prepareOtherUserDetails();

        // updated payment
        UserDetails.Payments payments = createUserResponse.getUserDetails().getPayments();
        // only one payment provided from data provider
        payments.getPayment().get(0).setExpiryYear(LocalDate.now().plusYears(5).getYear());
        payments.getPayment().get(0).setCardholder("Richard Roe");

        userDetailsToUpdate.setPayments(createUserResponse.getUserDetails().getPayments());
        userDetailsToUpdate.setId(createUserResponse.getUserDetails().getId());

        updateUserRequest.setUserDetails(userDetailsToUpdate);
        UpdateUserResponse updateUserResponse = client.updateUser(updateUserRequest);

        // then
        verifyAllUsersUpdatedData(updateUserRequest, updateUserResponse);
    }

    @Test(description = TC_UPDATE_USER_ADDRESS, dataProviderClass = UserDataProvider.class,
            dataProvider = "validUserWithAddresses")
    @Description(TC_UPDATE_USER_ADDRESS)
    @SneakyThrows
    public void updateUsersAddress(CreateUserRequest createUserRequest) {
        // given
        CreateUserResponse createUserResponse = client.createUser(createUserRequest);
        GeneralSteps.verifyAllUsersData(createUserRequest, createUserResponse);

        // when
        // updated basic user's details
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        UserDetails userDetailsToUpdate = prepareOtherUserDetails();

        // updated address
        UserDetails.Addresses addresses = createUserResponse.getUserDetails().getAddresses();
        // only one address provided from data provider
        addresses.getAddress().get(0).setCity("Updated city");
        addresses.getAddress().get(0).setZip("Updated zip");
        addresses.getAddress().get(0).setLine1("Updated line 1");
        addresses.getAddress().get(0).setLine2("Updated line 2");

        userDetailsToUpdate.setAddresses(createUserResponse.getUserDetails().getAddresses());
        userDetailsToUpdate.setId(createUserResponse.getUserDetails().getId());

        updateUserRequest.setUserDetails(userDetailsToUpdate);
        UpdateUserResponse updateUserResponse = client.updateUser(updateUserRequest);

        // then
        verifyAllUsersUpdatedData(updateUserRequest, updateUserResponse);
    }

}
