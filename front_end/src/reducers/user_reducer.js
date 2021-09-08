import {
    GET_SINGLE_USER_BEGIN,
    GET_SINGLE_USER_SUCCESS,
    GET_SINGLE_USER_ERROR,
    LOGOUT,
    GET_USERS_BEGIN,
    GET_USERS_SUCCESS,
    GET_USERS_ERROR,
    UPDATE_USER,
} from "../actions";

const user_reducer = (state, action) => {

    if (action.type === GET_SINGLE_USER_BEGIN) {
        return {
            ...state,
            single_user_loading: true,
            single_user_error: false,
        }
    }

    if (action.type === GET_SINGLE_USER_SUCCESS) {
        return {
            ...state,
            single_user_loading: false,
            myUser: action.payload,
        }
    }

    if (action.type === GET_SINGLE_USER_ERROR) {
        return {
            ...state,
            single_user_loading: false,
            single_user_error: true,
        }
    }


    if (action.type === GET_USERS_BEGIN) {
        return {
            ...state,
            users_loading: true,
            users_error: false,
        }
    }

    if (action.type === GET_USERS_SUCCESS) {
        return {
            ...state,
            users_loading: false,
            users: action.payload,
        }
    }

    if (action.type === GET_USERS_ERROR) {
        return {
            ...state,
            users_loading: false,
            users_error: true,
        }
    }

    if (action.type === UPDATE_USER) {
        const { data, index } = action.payload
        const tempUser = state.users.map((item, ind) => index === ind ? data : item)
        return {
            ...state,
            users: tempUser
        }

    }

    if (action.type === LOGOUT) {
        return {
            ...state,
            single_user_loading: false,
            myUser: null,
            single_user_error: false,
        }
    }

    throw new Error(`No Matching "${action.type}" - action type`)
}

export default user_reducer