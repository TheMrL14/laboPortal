import React from "react";
import SideNav from "../common/navigation/SideNav";

import * as AuthService from "../../auth/Auth";

const nav = [
    {
        id: "1",
        url: "./info",
        title: "Details",
        isVisable: true,
    },
    {
        id: "2",
        url: "./edit",
        title: "Edit",
        isVisable: AuthService.isLoggedIn(),
    },
    {
        id: "3",
        url: "./sop",
        title: "SOP",
        isVisable: true,
    },
    {
        id: "4",
        url: "./discussion",
        title: " Discussion",
        isVisable: AuthService.isLoggedIn(),
    },
    {
        id: "5",
        url: "./code",
        title: " Generate QR code",
        isVisable: true,
    },
];
const SideNavDevice = () => <SideNav links={nav}/>;

export default SideNavDevice;
