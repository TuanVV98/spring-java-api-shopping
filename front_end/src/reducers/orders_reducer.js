import {
    GET_ORDERS_BEGIN,
    GET_ORDERS_SUCCESS,
    GET_ORDERS_ERROR,
    GET_VALUE_ORDER,
    UPDATE_ORDERS,

} from '../actions'

const orders_reducer = (state, action) => {

    if (action.type === GET_ORDERS_BEGIN) {
        return { ...state, orders_loading: true }
    }

    if (action.type === GET_ORDERS_SUCCESS) {
        const featured_orders = action.payload.filter(
            (category) => category.featured === true
        )
        return {
            ...state,
            orders_loading: false,
            orders: action.payload,
            featured_orders,
        }
    }

    if (action.type === GET_ORDERS_ERROR) {
        return { ...state, orders_loading: false, orders_error: true }
    }

    if (action.type === GET_VALUE_ORDER) {
        const { value, index } = action.payload

        return {
            ...state,
            value_order: value,
            index_order: index,
        }
    }

    if (action.type === UPDATE_ORDERS) {
        const { data, index } = action.payload
        const tempOrder = state.orders.map((item, ind) => index === ind ? data : item)

        return {
            ...state,
            orders: tempOrder,
            index_order: -1,
        }
    }

    throw new Error(`No Matching "${action.type}" - action type`)
}

export default orders_reducer;