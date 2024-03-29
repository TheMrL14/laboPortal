let token = localStorage.getItem("id_token") || null;
let rootEndpoint = process.env.REACT_APP_API_ROOT_ENDPOINT;

export async function handleResponse(response) {

    if (response.status === 204) return response;

    if (response.ok) return response.json();

    if (response.status === 400) {
        // So, a server-side validation error occurred.
        // Server side validation returns a string error message, so parse as text instead of json.
        const error = await response.text();
        throw new Error(error);
    }
    throw new Error("Network response was not ok.");
}

// In a real app, would likely call an error logging service.
export function handleError(error) {
    // eslint-disable-next-line no-console
    console.error("API call failed. " + error);
    throw error;
}

export function getRequest(endpoint) {
    return fetch(rootEndpoint + endpoint)
        .then(handleResponse)
        .catch(handleError);
}

export function authenticatedPostRequest(endpoint, object) {
    object.id = !object.id || object.id === 0 || object.id === "" ? null : object.id;
    const id = object.id != null ? object.id : ""
    console.log(object.id)
    return fetch(rootEndpoint + endpoint + ("/" + id), {
        method: object.id != null ? "PUT" : "POST", // POST for create, PUT to update when id already exists.
        headers: {
            "content-type": "application/json",
            Authorization: "Bearer " + token,
        },
        body: JSON.stringify(object),
    })
        .then(handleResponse)
        .catch(handleError);
}

export function authenticatedPostFileRequest(endpoint, file) {
    const formData = new FormData();
    formData.append('file', file);
    return fetch(rootEndpoint + endpoint, {
        method: "POST",
        // headers: {
        //     Authorization: "Bearer " + token,
        // },
        body: formData
        ,
    })
        .then(handleResponse)
        .catch(handleError);
}

export function authenticatedDeleteRequest(endpoint, id) {
    return fetch(rootEndpoint + endpoint + "/" + id, {method: "DELETE"})
        .then(handleResponse)
        .catch(handleError);
}
