import React, {Component} from "react";
import {Redirect, Route, Switch} from "react-router-dom";
import Header from "./common/navigation/Header";
import PageNotFound from "./PageNotFound";
import DevicesPage from "./devices/DevicesPage";
import PropTypes from "prop-types";
import "../style/index.css";
import "../style/nav.css";

import SopsPage from "./sops/SopsPage";

import EditDevice from "./device/EditDevicePage";
import EditSop from "./sop/EditSopPage";
import DeviceSop from "./device/DeviceSopPage";
import DeviceCode from "./device/DeviceCode";
import SopDetailPage from "./sop/SopDetailPage";

import "primereact/resources/themes/bootstrap4-light-blue/theme.css";
import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";
import "primeflex/primeflex.css";
import PrivateRoute from "./common/navigation/PrivateRoute";

import {connect} from "react-redux";

import * as AuthService from "../auth/Auth";
import * as authActions from "../redux/actions/authActions";
import InfoPage from "./device/InfoPage";

class App extends Component {
    componentDidMount() {
        const {loginError, loginSuccess} = this.props;
        AuthService.lock.on("authenticated", (authResult) => {

            AuthService.lock.getUserInfo(authResult.accessToken, (error, profile) => {
                if (error) {
                    return loginError(error);
                }
                AuthService.setToken(authResult.idToken); // static method
                AuthService.setProfile(profile); // static method
                loginSuccess(profile);
                AuthService.lock.hide();
            });
        });

        AuthService.lock.on("authorization_error", (error) => {
            loginError(error);
        });
    }

    render = () => {
        return (
            <>
                <Header {...this.props} />

                <main>
                    <Switch>
                        <Redirect exact from="/" to="/devices" {...this.props}/>

                        <PrivateRoute
                            path="/devices/:slug/edit"
                            component={EditDevice}
                            {...this.props}
                        />
                        <PrivateRoute
                            path="/devices/create"
                            component={EditDevice}
                            {...this.props}
                        />

                        <PrivateRoute
                            path="/sops/:slug/edit"
                            component={EditSop}
                            {...this.props}
                        />
                        <PrivateRoute
                            path="/sops/create"
                            component={EditSop}
                            {...this.props}
                        />

                        <Route path="/devices/:slug/code" component={DeviceCode} {...this.props}/>
                        <Route path="/devices/:slug/info" component={InfoPage} {...this.props}/>
                        <Route path="/devices/:slug/sop" component={DeviceSop} {...this.props}/>

                        <Redirect from="/devices/:slug/" to="/devices/:slug/info" {...this.props}/>

                        <Route path="/devices" component={DevicesPage} {...this.props}/>

                        <Route path="/sops/:slug/info" component={SopDetailPage} {...this.props}/>
                        <Redirect from="/sops/:slug/" to="/sops/:slug/info" {...this.props}/>
                        <Route path="/sops" component={SopsPage}{...this.props} />
                        <Route component={PageNotFound}/>
                    </Switch>
                </main>
            </>
        );
    };
}

App.propTypes = {
    auth: PropTypes.object.isRequired,
    loginRequest: PropTypes.func.isRequired,
    loginError: PropTypes.func.isRequired,
    loginSuccess: PropTypes.func.isRequired,
};

const mapStateToProps = ({auth}) => ({auth});

const mapDispatchToProps = (dispatch) => ({
    loginRequest: (profile) => dispatch(authActions.loginRequest(profile)),
    loginSuccess: (profile) => dispatch(authActions.loginSuccess(profile)),
    logoutSuccess: (profile) => dispatch(authActions.logoutSucces(profile)),
    loginError: (error) => dispatch(authActions.loginError(error)),
});

export default connect(mapStateToProps, mapDispatchToProps)(App);
