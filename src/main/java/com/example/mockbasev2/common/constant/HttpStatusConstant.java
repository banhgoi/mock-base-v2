package com.example.mockbasev2.common.constant;

public class HttpStatusConstant {
    // success
    public final static String SUCCESS_CODE = "001";
    public final static String SUCCESS_MESSAGE = "Successful";

    // unknown error
    public final static String UNAVAILABLE_CODE = "002";
    public final static String UNAVAILABLE_MESSAGE = "Something wrong!";

    // sql exception
    public final static String SQL_CONNECTION_ERROR_CODE = "011";
    public final static String SQL_CONNECTION_ERROR_MESSAGE = "Some thing wrong with connection of database!";

    // unauthorized
    public final static String UNAUTHORIZED_CODE = "041";
    public final static String UNAUTHORIZED_MESSAGE = "You are unauthorized!";
    public final static String AUTHENTICATION_FAIL_CODE = "043";
    public final static String AUTHENTICATION_FAIL_MESSAGE = "Authentication failed!";

    // null pointer exception
    public final static String NULL_POINTER_OR_BAD_REQUEST_CODE = "005";
    public final static String NULL_POINTER_OR_BAD_REQUEST_MESSAGE = "You passed wrong or blank input data!";

    // not found
    public final static String NOT_FOUND_CODE = "004";
    public final static String INVENTORY_NOT_FOUND_MESSAGE = "User not found!";
    public final static String POST_NOT_FOUND_MESSAGE = "Post not found!";

    // invalid data
    public final static String INVALID_DATA_CODE = "006";
    public final static String INVALID_DATA_MESSAGE = "Please input valid data!";
}
