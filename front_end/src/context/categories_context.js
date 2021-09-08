import React, { useContext, useEffect, useReducer } from 'react'
import reducer from '../reducers/categories_reducer';
import { categories_url as url } from '../utils/constants';
import axios from 'axios';
import authHeader from "../service/Auth-header";
import {
    GET_CATEGORY_BEGIN,
    GET_CATEGORY_SUCCESS,
    GET_CATEGORY_ERROR,
    GET_VALUE_CATEGORY,
    UPDATE_CATEGORIES,
    ADD_TO_CATEGORY,
    REMOVE_CATEGORY_ITEM,
    GET_RECYCLE_CATEGORIES_BEGIN,
    GET_RECYCLE_CATEGORIES_SUCCESS,
    GET_RECYCLE_CATEGORIES_ERROR,
    ADD_TO_RECYCLE_CATEGORY,
    REMOVE_RECYCLE_CATEGORY_ITEM,
} from '../actions';

const initialState = {
    categories_loading: false,
    categories_error: false,
    categories: [],
    featured_categories: [],
    recycle_categories_loading: false,
    recycle_categories_error: false,
    recycle_categories: [],
    value_category: {},
    index_category: -1,
}

const CategoriesContext = React.createContext();

export const CategoriesProvider = ({ children }) => {

    const [state, dispatch] = useReducer(reducer, initialState)

    const fetchCategories = async (url) => {
        dispatch({ type: GET_CATEGORY_BEGIN })
        try {
            const response = await axios.get(url)
            const categories = response.data.data
            dispatch({ type: GET_CATEGORY_SUCCESS, payload: categories })
        } catch (error) {
            dispatch({ type: GET_CATEGORY_ERROR })
        }
    }

    const fetchRecycleCategories = async (url) => {
        dispatch({ type: GET_RECYCLE_CATEGORIES_BEGIN })
        try {
            const response = await axios.get(url, { headers: authHeader() })
            const categories = response.data.data
            dispatch({ type: GET_RECYCLE_CATEGORIES_SUCCESS, payload: categories })
        } catch (error) {
            dispatch({ type: GET_RECYCLE_CATEGORIES_ERROR })
        }
    }

    const addToCategory = (data) => {
        console.log(data)
        dispatch({ type: ADD_TO_CATEGORY, payload: data })
    }

    const addToRecycleCategory = (data) => {
        console.log(data)
        dispatch({ type: ADD_TO_RECYCLE_CATEGORY, payload: data })
    }
    const getValueCategory = (e, value, index) => {
        console.log(e.target.value)
        console.log(value)
        console.log(index)
        dispatch({ type: GET_VALUE_CATEGORY, payload: { value, index } })
    }
    const updateCategories = (data, index) => {
        console.log(data);
        dispatch({ type: UPDATE_CATEGORIES, payload: { data, index } })
    }

    const removeItem = (id) => {
        dispatch({ type: REMOVE_CATEGORY_ITEM, payload: id })
    }

    const removeRecycleItem = (id) => {
        dispatch({ type: REMOVE_RECYCLE_CATEGORY_ITEM, payload: id })
    }

    useEffect(() => {
        fetchCategories(url)
    }, [])

    return (
        <CategoriesContext.Provider
            value={{
                ...state, getValueCategory, updateCategories, addToCategory,
                removeItem, addToRecycleCategory, removeRecycleItem, fetchRecycleCategories
            }}
        >
            {children}
        </CategoriesContext.Provider>
    )
}

export const useCategoeiesContext = () => {
    return useContext(CategoriesContext);
}