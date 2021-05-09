import * as types from "../actions/actionTypes";

export default (state = [], action) => {
    switch (action.type) {
        case types.CREATE_DEVICE_SUCCESS:
            return [...state, {...action.device}];
        case types.UPDATE_DEVICE_SUCCESS:
            return state.map((device) =>
                device.id === action.device.id ? action.device : device
            );
        case types.LOAD_DEVICES_SUCCES:
            return action.devices;
        default:
            return state;
    }
};
