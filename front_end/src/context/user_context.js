import React, { useContext, useEffect, useReducer, useState } from 'react'
import reducer from "../reducers/user_reducer";
import axios from "axios";
import { user_url as url } from "../utils/constants"
import authHeader from "../service/Auth-header";
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

const initialState = {
    myUser: null,
    single_user_loading: false,
    single_user_error: false,

    users: [],
    users_loading: false,
    users_error: false,
}

const UserContext = React.createContext();

export const UserProvider = ({ children }) => {
    const [state, dispatch] = useReducer(reducer, initialState)

    // const [myUser, setMyUser] = useState(null)

    const fetchSingleUser = async (url) => {
        dispatch({ type: GET_SINGLE_USER_BEGIN })
        try {
            // console.log('true')
            const response = await axios.get(url, { headers: authHeader() })
            const user = response.data.data
            localStorage.setItem("myUser", JSON.stringify(user));
            dispatch({ type: GET_SINGLE_USER_SUCCESS, payload: user })
        } catch (error) {
            dispatch({ type: GET_SINGLE_USER_ERROR })
        }
    }

    const fetchUsers = async (url) => {
        dispatch({ type: GET_USERS_BEGIN })
        try {
            // console.log('true')
            const response = await axios.get(url, { headers: authHeader() })
            const user = response.data.data
            dispatch({ type: GET_USERS_SUCCESS, payload: user })
        } catch (error) {
            dispatch({ type: GET_USERS_ERROR })
        }
    }

    const updateUser = (data, index) => {
        dispatch({ type: UPDATE_USER, payload: { data, index } })
    }
    const logout = () => {
        dispatch({ type: LOGOUT })
    }

    useEffect(() => {
        fetchSingleUser(url)
    }, [])

    // console.log(myUser)

    return (
        <UserContext.Provider
            value={{ ...state, fetchSingleUser, fetchUsers,updateUser, logout }}
        >
            {children}
        </UserContext.Provider>
    )
}

// make sure use
export const useUserContext = () => {
    return useContext(UserContext)
}