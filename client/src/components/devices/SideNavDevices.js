import React from "react";
import SideNav from "../common/navigation/SideNav";
import * as AuthService from "../../auth/Auth";

const nav = [
    {
        id: "1",
        url: "/devices",
        title: "All Devices",
        isVisable: true,
    },
    {
        id: "2",
        url: "/devices/create",
        title: "Create Device",
        isVisable: AuthService.isLoggedIn(),
    },
];

const SideNavDevices = () => {
    return <SideNav links={nav}/>;
};

export default SideNavDevices;
