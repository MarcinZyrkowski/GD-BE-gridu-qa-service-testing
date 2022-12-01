package com.griddynamics.gridu.qa.user.tests.test_data;

import com.griddynamics.gridu.qa.address.api.model.Address;
import com.griddynamics.gridu.qa.user.CreateUserRequest;
import com.griddynamics.gridu.qa.user.NewAddress;
import com.griddynamics.gridu.qa.user.State;
import com.griddynamics.gridu.qa.user.utils.NumberRange;
import lombok.SneakyThrows;

import java.util.Collections;

import static com.griddynamics.gridu.qa.user.utils.EnumUtils.getRandomElement;

public class AddressTestData extends TestData {

    protected static final State STATE = getRandomElement(State.class);
    protected static final String CITY = "city";
    protected static final String CITY_2 = "city 2";
    protected static final String ZIP = "zip";
    protected static final String ZIP_2 = "zip 2";
    protected static final String LINE_1 = "address line 1";
    protected static final String LINE_1_2 = "address line 1.2";
    protected static final String LINE_2 = "address line 2";
    protected static final String LINE_2_2 = "address line 2.2";
    protected static final Address.StateEnum STATE_ENUM = getRandomElement(Address.StateEnum.class);
    protected static final NumberRange numberRange = new NumberRange(1, 10000);

    public static CreateUserRequest.Addresses prepareAddresses() {
        return OBJECT_FACTORY.createCreateUserRequestAddresses();
    }

    public static void addNewAddress(CreateUserRequest.Addresses addresses, NewAddress newAddress) {
        addresses.getAddress().add(newAddress);
    }

    public static NewAddress prepareNewAddress() {
        NewAddress address = OBJECT_FACTORY.createNewAddress();
        address.setState(STATE);
        address.setCity(CITY);
        address.setZip(ZIP);
        address.setLine1(LINE_1);
        address.setLine2(LINE_2);
        return address;
    }

    public static Address createAddressResponse() {
        Address address = new Address();
        address.setUserId(numberRange.randomLong());
        address.setId(numberRange.randomLong());
        address.setAddressLine1(LINE_1_2);
        address.setAddressLine2(LINE_2_2);
        address.setCity(CITY_2);
        address.setZip(ZIP_2);
        address.setState(STATE_ENUM);
        return address;
    }

    @SneakyThrows
    public static String createAddressResponseJSON() {
        return objectMapper.writeValueAsString(Collections.singletonList(createAddressResponse()));
    }

}
