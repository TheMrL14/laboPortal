import * as types from "../actions/actionTypes";

export default (state = [], action) => {
    switch (action.type) {
        case types.CREATE_SOP_SUCCESS:
            return [...state, {...action.sop}];
        case types.UPDATE_SOP_SUCCESS:
            return state.map((sop) => (sop.id === action.sop.id ? action.sop : sop));
        case types.LOAD_SOPS_SUCCES:
            return action.sops;
        default:
            return state;
    }
};
