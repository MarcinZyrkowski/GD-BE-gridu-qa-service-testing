package com.griddynamics.gridu.qa.user.tests.test_data;

import com.griddynamics.gridu.qa.user.CreateUserRequest;
import com.griddynamics.gridu.qa.user.NewAddress;
import com.griddynamics.gridu.qa.user.State;

import static com.griddynamics.gridu.qa.user.utils.EnumUtils.getRandomElement;

public class AddressTestData extends TestData {

    protected final static State STATE = getRandomElement(State.class);
    protected final static String CITY = "city";
    protected final static String ZIP = "zip";
    protected final static String LINE_1 = "line1";
    protected final static String LINE_2 = "line2";

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

}
