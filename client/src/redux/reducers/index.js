import {combineReducers} from "redux";
import devices from "./deviceReducer";
import sops from "./sopReducer";
import authReducer from "./authReducer";

const rootReducer = combineReducers({
    devices,
    sops,
    auth: authReducer,
});

export default rootReducer;
