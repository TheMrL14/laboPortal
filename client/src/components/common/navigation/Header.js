import React, {Component} from "react";
import {NavLink} from "react-router-dom";
import {FaClipboardList, FaFileImport, FaMicroscope, FaUser} from "react-icons/fa";
import PropTypes from "prop-types";

import * as AuthService from "../../../auth/Auth";
import FileInputDialog from "../input/FileInputDialog";

class Header extends Component {
    constructor(props) {
        super(props);
        this.state = {
            visible: false
        };
    }

    handleLoginClick = () => {
        AuthService.login();
        this.props.loginRequest();
    };

    handleLogoutClick = () => {
        this.props.logoutSuccess();
        AuthService.logout(); // careful, this is a static method
    };

    handleHideDialog = () => {
        this.setState({visible: false})
    }

    render = () => {
        const {auth} = this.props;

        const profile = AuthService.getProfile();


        const profileButton = (
            <NavLink to="/profile" activeClassName="active">
        <span className="navLogo">
          <FaUser/>
        </span>
                <span className="navTitle">{profile.name}</span>
            </NavLink>
        );

        const loginButton = (
            <a onClick={this.handleLoginClick}>
        <span className="navLogo">
          <FaUser/>
        </span>
                <span className="navTitle">Login</span>
            </a>
        );

        const logoutButton = (
            <a onClick={this.handleLogoutClick}>
                <span className="navLogo">
                    <FaUser/>
                </span>
                <span className="navTitle">LogOut</span>
            </a>
        );

        return (
            <>
                <header>
                    <nav>
                        {auth.isAuthenticated ? profileButton : loginButton}
                        <NavLink to="/devices" activeClassName="active">
                        <span className="navLogo">
                            <FaMicroscope/>
                        </span>
                            <span className="navTitle">Devices</span>
                        </NavLink>
                        <NavLink to="/sops" activeClassName="active">
                        <span className="navLogo">
                            <FaClipboardList/>
                        </span>
                            <span className="navTitle">Sops</span>
                        </NavLink>
                        <a onClick={e => this.setState({visible: true})}>
                         <span className="navLogo">
                            <FaFileImport/>
                        </span>
                            <span className="navTitle">Import PDF</span>
                        </a>

                        {auth.isAuthenticated ? logoutButton : null}
                    </nav>
                </header>
                <FileInputDialog
                    visible={this.state.visible}
                    onHide={this.handleHideDialog}
                />
            </>
        );
    };
}

Header.propTypes = {
    auth: PropTypes.object.isRequired,
    loginRequest: PropTypes.func.isRequired,
    logoutSuccess: PropTypes.func.isRequired,
};

export default Header;
