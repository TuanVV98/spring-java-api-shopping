import React, { useContext, useEffect, useReducer } from 'react'
import reducer from '../reducers/orders_reducer';
import { orders_url as url } from '../utils/constants';
import authHeader from "../service/Auth-header";
import axios from 'axios';
import {
    GET_ORDERS_BEGIN,
    GET_ORDERS_SUCCESS,
    GET_ORDERS_ERROR,
    GET_VALUE_ORDER,
    UPDATE_ORDERS,
} from '../actions';

const initialState = {
    orders_loading: false,
    orders_error: false,
    orders: [],
    featured_orders: [],
    value_order: {},
    index_order: -1,
}

const OrdersContext = React.createContext();

export const OrdersProvider = ({ children }) => {

    const [state, dispatch] = useReducer(reducer, initialState)

    const fetchOrders = async (url) => {
        dispatch({ type: GET_ORDERS_BEGIN })
        try {
            const response = await axios.get(url, { headers: authHeader() })
            const orders = response.data.data
            dispatch({ type: GET_ORDERS_SUCCESS, payload: orders })
        } catch (error) {
            dispatch({ type: GET_ORDERS_ERROR })
        }
    }

    const getValueOrder = (e, value, index) => {
        console.log(e.target.value)
        console.log(value)
        console.log(index)
        dispatch({ type: GET_VALUE_ORDER, payload: { value, index } })
    }

    const updateOrders = (data, index) => {
        console.log(data);
        dispatch({ type: UPDATE_ORDERS, payload: { data, index } })
    }

    useEffect(() => {
        fetchOrders(url)
    }, [])

    return (
        <OrdersContext.Provider
            value={{ ...state, getValueOrder, updateOrders}}
        >
            {children}
        </OrdersContext.Provider>
    )
}

export const useOrdersContext = () => {
    return useContext(OrdersContext);
}