import Auth0Lock from "auth0-lock";
import jwtDecode from "jwt-decode";

// Configure Auth0 lock
export const lock = new Auth0Lock(
    process.env.REACT_APP_AUTH0_CLIENT_ID,
    process.env.REACT_APP_AUTH0_DOMAIN,
    {
        auth: {
            redirect: false,
            responseType: "token id_token",
            sso: false
        },
        theme: {
            primaryColor: "#b81b1c",
        },
        languageDictionary: {
            title: "React Redux Auth0 Kit",
        },
    }
);

export const login = () => {
    lock.show();
};

export const isLoggedIn = () => {
    // Checks if there is a saved token and it's still valid
    const token = getToken();
    return token != null && !isTokenExpired();
};

export const logout = () => {
    // Clear user token and profile data from window.localStorage
    lock.logout();
    window.localStorage.removeItem("id_token");
    window.localStorage.removeItem("profile");
};

export const getProfile = () => {
    // Retrieves the profile data from window.localStorage
    const profile = window.localStorage.getItem("profile");
    return profile ? JSON.parse(window.localStorage.profile) : {};
};

export const setProfile = (profile) => {
    // Saves profile data to window.localStorage
    window.localStorage.setItem("profile", JSON.stringify(profile));
    // Triggers profile_updated event to update the UI
};

export const setToken = (idToken) => {
    // Saves user token to window.localStorage
    window.localStorage.setItem("id_token", idToken);
};

export const getToken = () => {
    // Retrieves the user token from window.localStorage
    return window.localStorage.getItem("id_token");
};

export const getTokenExpirationDate = () => {
    const token = getToken();
    const decoded = jwtDecode(token);
    if (!decoded.exp) {
        return null;
    }

    const date = new Date(0);
    date.setUTCSeconds(decoded.exp);
    return date;
};

export const isTokenExpired = () => {
    const token = getToken();

    if (token == null) return true;
    const date = getTokenExpirationDate();
    const offsetSeconds = 0;
    if (date === null) {

        return false;
    }
    return (date.valueOf() <= new Date().valueOf() + offsetSeconds * 1000);
};
