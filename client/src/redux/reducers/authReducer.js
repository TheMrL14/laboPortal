import * as AuthService from "../../auth/Auth";
import {LOGIN_FAILURE, LOGIN_REQUEST, LOGIN_SUCCESS, LOGOUT_SUCCESS,} from "../actions/actionTypes";

const authReducer = (
    state = {
        isAuthenticated: !AuthService.isTokenExpired(),
        isFetching: false,
        user: AuthService.getProfile(),
        error: null,
    },
    action
) => {
    switch (action.type) {
        case LOGIN_REQUEST:
            return {
                ...state,
                isFetching: true,
                error: null,
            };
        case LOGIN_SUCCESS:
            return {
                ...state,
                isFetching: false,
                isAuthenticated: true,
                user: action.user,
            };
        case LOGIN_FAILURE:
            return {
                ...state,
                isFetching: false,
                isAuthenticated: false,
                user: {},
                error: action.error,
            };
        case LOGOUT_SUCCESS:
            return {
                ...state,
                isAuthenticated: false,
                user: {},
            };
        default:
            return state;
    }
};

export default authReducer;
