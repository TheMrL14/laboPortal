import {LOGIN_FAILURE, LOGIN_REQUEST, LOGIN_SUCCESS, LOGOUT_SUCCESS,} from "./actionTypes";

export function loginRequest() {
    return {
        type: LOGIN_REQUEST,
    };
}

export function loginSuccess(user) {
    return {
        type: LOGIN_SUCCESS,
        user: user,
    };
}

export function loginError(message) {
    return {
        type: LOGIN_FAILURE,
        isFetching: false,
        isAuthenticated: false,
        message,
    };
}

export function receiveLogin(user) {
    return function (dispatch) {
        return dispatch(loginSuccess(user));
    };
}

export function logoutSucces() {
    return {
        type: LOGOUT_SUCCESS,
    };
}
