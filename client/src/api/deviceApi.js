import {authenticatedDeleteRequest, authenticatedFormPostRequest, getRequest,} from "./apiUtils";

const endpoint = "/devices";

export function getDevices() {
    return getRequest(endpoint);
}

export function saveDevice(device) {
    const formData = new FormData();
    console.log(device.videos.length)
    for (let x = 0; x < device.videos.length; x++) {
        console.log(device.videos[x])
        formData.append('video', device.videos[x]);
    }
    device.videos = null;
    formData.append("device", new Blob([JSON.stringify(device)], {
        type: "application/json"
    }))
    // formData.append('device', device.id || 0);
    // formData.append('deviceName', device.name);
    // formData.append('description', device.description);
    // formData.append('sop', device.sop);
    // formData.append('image', device.image);
    // formData.append('imageName', device.imageName);
    // formData.append('externalLinks', device.externalLinks);
    // formData.append('videos', device.videos);
    return authenticatedFormPostRequest(endpoint, formData, device.id);
}

export function deleteDevice(id) {
    return authenticatedDeleteRequest(endpoint, id);
}
