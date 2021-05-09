import * as types from "./actionTypes";
import * as sopApi from "../../api/sopApi";

export function loadSopsSucces(sops) {
    return {type: types.LOAD_SOPS_SUCCES, sops};
}

export function createSopSuccess(sop) {
    return {type: types.CREATE_SOP_SUCCESS, sop};
}

export function updateSopSuccess(sop) {
    return {type: types.UPDATE_SOP_SUCCESS, sop};
}

export function loadSops() {
    return function (dispatch) {
        return sopApi
            .getSops()
            .then((sops) => {
                dispatch(loadSopsSucces(sops));
            })
            .catch((err) => {
                throw err;
            });
    };
}

export function saveSop(sop) {
    //eslint-disable-next-line no-unused-vars
    return function (dispatch, getState) {
        return sopApi
            .saveSop(sop)
            .then((savedSop) => {
                sop.id
                    ? dispatch(updateSopSuccess(savedSop))
                    : dispatch(createSopSuccess(savedSop));
            })
            .catch((error) => {
                throw error;
            });
    };
}
