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
];
const SideNavSop = () => <SideNav links={nav}/>;

export default SideNavSop;
