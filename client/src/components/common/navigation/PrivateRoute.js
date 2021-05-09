//src : https://app.pluralsight.com/library/courses/react-auth0-authentication-security

import React, {Component} from "react";
import PropTypes from "prop-types";
import {Route} from "react-router";

import * as AuthService from "../../../auth/Auth";

const PrivateRoute = (props) => {
    const {auth, component: Component} = props;

    return (
        <Route
            {...props}
            render={(props) => {
                // 1. Redirect to login if not logged in.
                if (!auth.isAuthenticated) {
                    AuthService.login();
                    return null;
                }
                /*
                // 2. Display message if user lacks required scope(s).
                if (scopes.length > 0 && !auth.userHasScopes(scopes)) {
                  return (
                    <h1 className="loading">
                      Unauthorized - You need the following scope(s) to view this page:{" "}
                      {scopes.join(",")}.
                    </h1>
                  );
                }
                */
                // 3. Render component

                return <Component auth={auth} {...props} />;
            }}
        />
    );
};

PrivateRoute.propTypes = {
    auth: PropTypes.object.isRequired,
    component: PropTypes.func,
    loginRequest: PropTypes.func.isRequired,
};

export default PrivateRoute;
