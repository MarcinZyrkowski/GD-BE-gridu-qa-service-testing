package com.griddynamics.gridu.qa.user.tf.all_tests.e2e.tests;

import com.griddynamics.gridu.qa.user.*;
import com.griddynamics.gridu.qa.user.tf.all_tests.e2e.UserManagementE2EBaseTest;
import com.griddynamics.gridu.qa.user.tf.steps.UserManagementSteps;
import com.griddynamics.gridu.qa.user.tf.test_data.PaymentTestData;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lombok.SneakyThrows;
import org.testng.annotations.Test;

import static com.griddynamics.gridu.qa.user.tf.report.e2e.UserManagementE2EFeatures.*;
import static com.griddynamics.gridu.qa.user.tf.test_data.AddressTestData.*;
import static com.griddynamics.gridu.qa.user.tf.test_data.PaymentTestData.*;
import static com.griddynamics.gridu.qa.user.tf.test_data.UserTestData.prepareBasicCreateUserRequestData;
import static com.griddynamics.gridu.qa.user.tf.test_data.UserTestData.prepareOtherUserDetails;

@Feature(FEATURE_USER)
@Story(PBI_UPDATE_USER)
public class UpdateUserTest extends UserManagementE2EBaseTest {

    @Test(description = TC_UPDATE_USER)
    @Description(TC_UPDATE_USER)
    @SneakyThrows
    public void updateUsersData() {
        // given
        CreateUserRequest createUserRequest = prepareBasicCreateUserRequestData();
        CreateUserResponse createUserResponse = client.createUser(createUserRequest);
        UserManagementSteps.verifyBasicUserData(createUserRequest, createUserResponse);

        // when
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        UserDetails userDetailsToUpdate = prepareOtherUserDetails();
        userDetailsToUpdate.setId(createUserResponse.getUserDetails().getId());

        // then
        updateUserRequest.setUserDetails(userDetailsToUpdate);
        UpdateUserResponse updateUserResponse = client.updateUser(updateUserRequest);
        UserManagementSteps.verifyUpdatedUserDetails(updateUserRequest, updateUserResponse);
    }

    // update addresses

    // update payments

    @Test(description = TC_UPDATE_USER)
    @Description(TC_UPDATE_USER)
    @SneakyThrows
    public void updateUsersDataw() {
        CreateUserRequest createUserRequest = prepareBasicCreateUserRequestData();

        CreateUserRequest.Addresses addresses = prepareAddresses();
        addNewAddress(addresses, prepareNewAddress());

        CreateUserRequest.Payments payments = preparePayments();
        String cardHolder = createUserRequest.getName() + " " + createUserRequest.getLastName();
        addNewPayment(payments, prepareNewPayment(cardHolder));

        createUserRequest.setAddresses(addresses);
        createUserRequest.setPayments(payments);



        CreateUserResponse createUserResponse = client.createUser(createUserRequest);


        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        UserDetails userDetailsToUpdate = prepareOtherUserDetails();
//        userDetailsToUpdate.setPayments(PaymentTestData.createUDP());
//
        userDetailsToUpdate.setId(createUserResponse.getUserDetails().getId());

        updateUserRequest.setUserDetails(userDetailsToUpdate);

        UpdateUserResponse updateUserResponse = client.updateUser(updateUserRequest);

        System.out.println("x");
    }

}
