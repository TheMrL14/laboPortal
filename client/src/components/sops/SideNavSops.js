import React from "react";
import SideNav from "../common/navigation/SideNav";

import * as AuthService from "../../auth/Auth";

const nav = [
    {
        id: "1",
        url: "/sops",
        title: "All Sops",
        isVisable: true,
    },
    {
        id: "2",
        url: "/sops/create",
        title: "Create Sop",
        isVisable: AuthService.isLoggedIn(),
    },
];
const SideNavSops = () => <SideNav links={nav}/>;

export default SideNavSops;
