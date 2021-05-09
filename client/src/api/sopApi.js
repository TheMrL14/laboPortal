import {
    authenticatedDeleteRequest,
    authenticatedPostFileRequest,
    authenticatedPostRequest,
    getRequest,
} from "./apiUtils";

const endpoint = "/sops";

export function getSops() {
    return getRequest(endpoint);
}

export function saveSop(sop) {
    return authenticatedPostRequest(endpoint, sop);
}

export function importSop(file) {
    return authenticatedPostFileRequest(endpoint + "/upload", file)
}

export function deleteSop(id) {
    return authenticatedDeleteRequest(endpoint, id);
}
