import {authenticatedDeleteRequest, authenticatedPostRequest, getRequest,} from "./apiUtils";

const endpoint = "/devices/";

export function getDevices() {
    return getRequest(endpoint);
}

export function saveDevice(device) {
    return authenticatedPostRequest(endpoint, device);
}

export function deleteDevice(id) {
    return authenticatedDeleteRequest(endpoint, id);
}
