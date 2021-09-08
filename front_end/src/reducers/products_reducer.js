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
    ADD_TO_RECYCLE_PRODUCT,
    REMOVE_RECYCLE_PRODUCT_ITEM,
    GET_RECYCLE_PRODUCTS_BEGIN,
    GET_RECYCLE_PRODUCTS_SUCCESS,
} from '../actions'

const products_reducer = (state, action) => {

    switch (action.type) {
        case GET_PRODUCTS_BEGIN:
            return { ...state, products_loading: true }
        case GET_PRODUCTS_SUCCESS:
            const featured_products = action.payload.filter(
                (product) => product.featured === true
            )
            return {
                ...state,
                products_loading: false,
                products: action.payload,
                featured_products,
            }
        case GET_PRODUCTS_ERROR:
            return { ...state, products_loading: false, products_error: true }
        case GET_RECYCLE_PRODUCTS_BEGIN:
            return { ...state, recycle_products_loading: true }
        case GET_RECYCLE_PRODUCTS_SUCCESS:
            return {
                ...state,
                recycle_products_loading: false,
                recycle_products: action.payload,
            }
        case GET_PRODUCTS_ERROR:
            return { ...state, recycle_products_loading: false, recycle_products_error: true }
        case GET_SINGLE_PRODUCT_BEGIN:
            return {
                ...state,
                single_product_loading: true,
                single_product_error: false,

            }
        case GET_SINGLE_PRODUCT_SUCCESS:
            return {
                ...state,
                single_product_loading: false,
                single_product: action.payload,
            }
        case GET_SINGLE_PRODUCT_ERROR:
            return {
                ...state,
                single_product_loading: false,
                single_product_error: true,
            }
        case ADD_TO_PRODUCT:
            return { ...state, products: [...state.products, action.payload] }
        case ADD_TO_RECYCLE_PRODUCT:
            return { ...state, recycle_products: [...state.recycle_products, action.payload] }
        case GET_VALUE_PRODUCT:
            {
                const { value, index } = action.payload
                return {
                    ...state,
                    value_product: value,
                    index_product: index,
                }
            }
        case UPDATE_PRODUCTS:
            {
                const { data, index } = action.payload
                const tempProduct = state.products.map((item, ind) => index === ind ? data : item)
                return {
                    ...state,
                    products: tempProduct,
                    index_product: -1,
                }
            }
        case REMOVE_PRODUCT_ITEM:
            {
                const tempProduct = state.products.filter((item) => item.id !== action.payload)
                return { ...state, products: tempProduct }
            }
        case REMOVE_RECYCLE_PRODUCT_ITEM:
            {
                const tempProduct = state.recycle_products.filter((item) => item.id !== action.payload)
                return { ...state, recycle_products: tempProduct }
            }
        default:
            throw new Error(`No Matching "${action.type}" - action type`)
    }
}
//     if (action.type === GET_PRODUCTS_BEGIN) {
//         return { ...state, products_loading: true }
//     }

//     if (action.type === GET_PRODUCTS_SUCCESS) {
//         const featured_products = action.payload.filter(
//             (product) => product.featured === true
//         )
//         return {
//             ...state,
//             products_loading: false,
//             products: action.payload,
//             featured_products,
//         }
//     }

//     if (action.type === GET_PRODUCTS_ERROR) {
//         return { ...state, products_loading: false, products_error: true }
//     }

//     if (action.type === GET_SINGLE_PRODUCT_BEGIN) {
//         return {
//             ...state,
//             single_product_loading: true,
//             single_product_error: false,

//         }
//     }

//     if (action.type === GET_SINGLE_PRODUCT_SUCCESS) {
//         return {
//             ...state,
//             single_product_loading: false,
//             single_product: action.payload,
//         }
//     }

//     if (action.type === GET_SINGLE_PRODUCT_ERROR) {
//         return {
//             ...state,
//             single_product_loading: false,
//             single_product_error: true,
//         }
//     }

//     if (action.type === ADD_TO_PRODUCT) {
//         return { ...state, products: [...state.products, action.payload] }
//     }

//     if (action.type === GET_VALUE_PRODUCT) {
//         const { value, index } = action.payload
//         return {
//             ...state,
//             value_product: value,
//             index_product: index,
//         }
//     }

//     if (action.type === UPDATE_PRODUCTS) {
//         const { data, index } = action.payload
//         const tempProduct = state.products.map((item, ind) => index === ind ? data : item)
//         return {
//             ...state,
//             products: tempProduct,
//             index_product: -1,
//         }
//     }

//     if (action.type === REMOVE_PRODUCT_ITEM) {
//         const tempProduct = state.products.filter((item) => item.id !== action.payload)
//         return { ...state, products: tempProduct }
//     }

//     throw new Error(`No Matching "${action.type}" - action type`)
// }

export default products_reducer;