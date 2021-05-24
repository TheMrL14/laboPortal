import React from "react";
import {NavLink} from "react-router-dom";
import PropTypes from "prop-types";

const SideNav = (props) => {
    return (
        <aside>
            <input className="menu-btn" type="checkbox" id="menu-btn"/>
            <label className="menu-icon" htmlFor="menu-btn">
                <span className="navIcon"></span>
            </label>
            <ul className="menu">
                {props.links.map((link) =>
                    link.isVisable ? (
                        <li key={link.id}>
                            <NavLink to={link.url} activeClassName="active" exact>
                                {link.title}
                            </NavLink>
                        </li>
                    ) : null
                )}
            </ul>
        </aside>
    );
};

SideNav.propTypes = {
    links: PropTypes.array.isRequired,
};

export default SideNav;
