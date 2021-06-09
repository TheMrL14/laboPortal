import * as types from "./actionTypes";
import * as deviceApi from "../../api/deviceApi";

export function loadDevicesSuccess(devices) {
    return {type: types.LOAD_DEVICES_SUCCES, devices};
}

export function createDeviceSuccess(device) {
    return {type: types.CREATE_DEVICE_SUCCESS, device};
}

export function updateDeviceSuccess(device) {
    return {type: types.UPDATE_DEVICE_SUCCESS, device};
}


export function deleteDeviceSuccess(device) {
    return {type: types.DELETE_DEVICE, device};
}

export function loadDevices() {
    //eslint-disable-next-line no-unused-vars
    return function (dispatch) {
        return deviceApi
            .getDevices()
            .then((devices) => {
                dispatch(loadDevicesSuccess(devices));
            })
            .catch((err) => {
                throw err;
            });
    };
}

export function saveDevice(device) {
    //eslint-disable-next-line no-unused-vars
    return function (dispatch) {
        return deviceApi
            .saveDevice(device)
            .then((savedCourse) => {
                device.id
                    ? dispatch(updateDeviceSuccess(savedCourse))
                    : dispatch(createDeviceSuccess(savedCourse));
            })
            .catch((error) => {
                throw error;
            });
    };
}

export function deleteDevice(device) {
    //eslint-disable-next-line no-unused-vars
    return function (dispatch) {
        return deviceApi
            .deleteDevice(device.id)
            .then(() => {
                dispatch(deleteDeviceSuccess(device))
            })
            .catch((error) => {
                throw error;
            });
    };
}
