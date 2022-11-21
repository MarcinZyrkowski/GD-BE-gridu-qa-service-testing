package com.griddynamics.gridu.qa.user.tests.test_data;

import com.griddynamics.gridu.qa.user.*;
import com.griddynamics.gridu.qa.user.utils.NumberRange;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.time.LocalDate;
import java.time.Month;

import static com.griddynamics.gridu.qa.user.utils.EnumUtils.getRandomElement;

public
class CreateNewUserTestData {

    protected final static ObjectFactory OBJECT_FACTORY = new ObjectFactory();
    protected final static String NAME = "John";
    protected final static String SURNAME = "Doe";
    protected final static String EMAIL = "john.doe@email.com";
    protected final static State STATE = getRandomElement(State.class);
    protected final static String CITY = "city";
    protected final static String ZIP = "zip";
    protected final static String LINE_1 = "line1";
    protected final static String LINE_2 = "line2";
    protected final static int EXPIRY_YEAR = LocalDate.now().plusYears(1).getYear();
    protected final static int EXPIRY_MONTH = LocalDate.now().plusYears(1).getMonthValue();
    protected final static String CARD_HOLDER = NAME + " " + SURNAME;
    private static final NumberRange monthsRange = new NumberRange(1, 12);
    protected final static LocalDate BIRTHDAY =
            LocalDate.of(2000, getRandomElement(Month.class), monthsRange.randomInt());
    private static final int DIGITS_NUMBER_IN_CARD_NUMBER = 16;
    protected final static String CARD_NUMBER = createRandomDigitSequence(DIGITS_NUMBER_IN_CARD_NUMBER);
    private static final int DIGITS_IN_CVV = 3;
    protected final static String CVV = createRandomDigitSequence(DIGITS_IN_CVV);

    public static CreateUserRequest prepareBasicCreateUserRequestData() throws DatatypeConfigurationException {
        CreateUserRequest createUserRequest = OBJECT_FACTORY.createCreateUserRequest();
        createUserRequest.setName(NAME);
        createUserRequest.setLastName(SURNAME);
        createUserRequest.setEmail(EMAIL);
        createUserRequest.setBirthday(DatatypeFactory.newInstance().newXMLGregorianCalendar(BIRTHDAY.toString()));
        return createUserRequest;
    }

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

    public static CreateUserRequest.Payments preparePayments() {
        return OBJECT_FACTORY.createCreateUserRequestPayments();
    }

    public static void addNewPayment(CreateUserRequest.Payments payments, NewPayment newPayment) {
        payments.getPayment().add(newPayment);
    }

    public static NewPayment prepareNewPayment() {
        NewPayment payment = OBJECT_FACTORY.createNewPayment();
        payment.setCardNumber(CARD_NUMBER);
        payment.setExpiryYear(EXPIRY_YEAR);
        payment.setExpiryMonth(EXPIRY_MONTH);
        payment.setCardholder(CARD_HOLDER);
        payment.setCvv(CVV);
        return payment;
    }

    public static String createRandomDigitSequence(int digitSequenceLength) {
        NumberRange digit = new NumberRange(0, 9);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digitSequenceLength; i++) {
            sb.append(digit.randomInt());
        }
        return sb.toString();
    }

}