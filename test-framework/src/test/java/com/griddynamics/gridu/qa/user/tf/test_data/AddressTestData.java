package com.griddynamics.gridu.qa.user.tf.test_data;

import com.griddynamics.gridu.qa.address.api.model.Address;
import com.griddynamics.gridu.qa.user.CreateUserRequest;
import com.griddynamics.gridu.qa.user.NewAddress;
import com.griddynamics.gridu.qa.user.State;
import com.griddynamics.gridu.qa.user.tf.utils.EnumUtils;
import com.griddynamics.gridu.qa.user.tf.utils.NumberRange;
import io.qameta.allure.Step;
import lombok.SneakyThrows;

import java.util.Collections;

public class AddressTestData extends TestData {

    protected static final State STATE = EnumUtils.getRandomElement(State.class);
    protected static final String CITY = "city";
    protected static final String CITY_2 = "city 2";
    protected static final String ZIP = "zip";
    protected static final String ZIP_2 = "zip 2";
    protected static final String LINE_1 = "address line 1";
    protected static final String LINE_1_2 = "address line 1.2";
    protected static final String LINE_2 = "address line 2";
    protected static final String LINE_2_2 = "address line 2.2";
    protected static final Address.StateEnum STATE_ENUM = EnumUtils.getRandomElement(Address.StateEnum.class);
    protected static final NumberRange NUMBER_RANGE = new NumberRange(1, 10000);
    protected static final long MOCK_USER_ID = NUMBER_RANGE.randomLong();
    protected static final long MOCK_ADDRESS_ID = NUMBER_RANGE.randomLong();

    public static CreateUserRequest.Addresses prepareAddresses() {
        return OBJECT_FACTORY.createCreateUserRequestAddresses();
    }

    @Step("Add new address")
    public static void addNewAddress(CreateUserRequest.Addresses addresses, NewAddress newAddress) {
        addresses.getAddress().add(newAddress);
    }

    @Step("Prepare new address")
    public static NewAddress prepareNewAddress() {
        NewAddress address = OBJECT_FACTORY.createNewAddress();
        address.setState(STATE);
        address.setCity(CITY);
        address.setZip(ZIP);
        address.setLine1(LINE_1);
        address.setLine2(LINE_2);
        return address;
    }

    @Step("Prepare mock new address")
    public static NewAddress prepareMockNewAddress() {
        NewAddress address = OBJECT_FACTORY.createNewAddress();
        address.setState(State.fromValue(STATE_ENUM.toString()));
        address.setCity(CITY_2);
        address.setZip(ZIP_2);
        address.setLine1(LINE_1_2);
        address.setLine2(LINE_2_2);
        return address;
    }

    @Step("Create mock address response")
    public static Address createMockAddressResponse() {
        Address address = new Address();
        address.setUserId(MOCK_USER_ID);
        address.setId(MOCK_ADDRESS_ID);
        address.setAddressLine1(LINE_1_2);
        address.setAddressLine2(LINE_2_2);
        address.setCity(CITY_2);
        address.setZip(ZIP_2);
        address.setState(STATE_ENUM);
        return address;
    }

    @SneakyThrows
    @Step("Create address response as JSON")
    public static String createAddressResponseJSON() {
        return objectMapper.writeValueAsString(Collections.singletonList(createMockAddressResponse()));
    }

}
