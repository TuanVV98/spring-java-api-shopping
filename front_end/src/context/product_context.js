import axios from 'axios';
import React, { useContext, useEffect, useReducer } from 'react';
import reducer from '../reducers/products_reducer';
import { products_url as url } from '../utils/constants';
import authHeader from "../service/Auth-header";
import {
    GET_PRODUCTS_BEGIN,
    GET_PRODUCTS_SUCCESS,
    GET_PRODUCTS_ERROR,
    GET_SINGLE_PRODUCT_BEGIN,
    GET_SINGLE_PRODUCT_SUCCESS,
    GET_SINGLE_PRODUCT_ERROR,
    GET_VALUE_PRODUCT,
    UPDATE_PRODUCTS,
    REMOVE_PRODUCT_ITEM,
    ADD_TO_PRODUCT,
    GET_RECYCLE_PRODUCTS_BEGIN,
    GET_RECYCLE_PRODUCTS_SUCCESS,
    GET_RECYCLE_PRODUCTS_ERROR,
    ADD_TO_RECYCLE_PRODUCT,
    REMOVE_RECYCLE_PRODUCT_ITEM,
} from '../actions'

const initialState = {
    products_loading: false,
    products_error: false,
    products: [],
    featured_products: [],
    single_product_loading: false,
    single_product_error: false,
    single_product: {},
    recycle_products_loading: false,
    recycle_products_error: false,
    recycle_products: [],
    value_product: {},
    index_product: -1,
}

const ProductsContext = React.createContext();

export const ProductsProvider = ({ children }) => {

    const [state, dispatch] = useReducer(reducer, initialState);

    const fetchProducts = async (url) => {

        dispatch({ type: GET_PRODUCTS_BEGIN })
        try {
            const response = await axios.get(url)
            const products = response.data.data;
            // console.log(response);
            dispatch({ type: GET_PRODUCTS_SUCCESS, payload: products })
        } catch (error) {
            dispatch({ type: GET_PRODUCTS_ERROR })
        }
    }

    const fetchSingleProduct = async (url) => {

        dispatch({ type: GET_SINGLE_PRODUCT_BEGIN })
        try {
            const response = await axios.get(url)
            const product = response.data.data;

            dispatch({ type: GET_SINGLE_PRODUCT_SUCCESS, payload: product })
        } catch (error) {
            dispatch({ type: GET_SINGLE_PRODUCT_ERROR })
        }
    }

    const fetchRecycleProduct = async (url) => {

        dispatch({ type: GET_RECYCLE_PRODUCTS_BEGIN })
        try {
            const response = await axios.get(url, { headers: authHeader() })
            const product = response.data.data;

            dispatch({ type: GET_RECYCLE_PRODUCTS_SUCCESS, payload: product })
        } catch (error) {
            dispatch({ type: GET_RECYCLE_PRODUCTS_ERROR })
        }
    }

    const addToProduct = (data) => {
        dispatch({ type: ADD_TO_PRODUCT, payload: data })
    }
    const addToRecycleProduct = (data) => {
        dispatch({ type: ADD_TO_RECYCLE_PRODUCT, payload: data })
    }

    const getValueProduct = (value, index) => {
        console.log(value)
        console.log(index)
        dispatch({ type: GET_VALUE_PRODUCT, payload: { value, index } })
    }

    const updateProducts = (data, index) => {
        dispatch({ type: UPDATE_PRODUCTS, payload: { data, index } })
    }

    const removeItem = (id) => {
        dispatch({ type: REMOVE_PRODUCT_ITEM, payload: id })
    }

    const removeRecycleItem = (id) => {
        dispatch({ type: REMOVE_RECYCLE_PRODUCT_ITEM, payload: id })
    }

    useEffect(() => {
        fetchProducts(url)
    }, [])


    return (
        <ProductsContext.Provider
            value={{
                ...state, fetchSingleProduct, addToProduct, getValueProduct, updateProducts,
                removeItem, fetchRecycleProduct, addToRecycleProduct, removeRecycleItem
            }}
        >
            {children}
        </ProductsContext.Provider>
    )
}

// make sure use
export const useProductsContext = () => {
    return useContext(ProductsContext);
}