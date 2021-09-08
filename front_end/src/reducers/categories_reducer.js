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
} from '../actions'

const categories_reducer = (state, action) => {

    if (action.type === GET_CATEGORY_BEGIN) {
        return { ...state, categories_loading: true }
    }

    if (action.type === GET_CATEGORY_SUCCESS) {
        const featured_categories = action.payload.filter(
            (category) => category.featured === true
        )
        return {
            ...state,
            categories_loading: false,
            categories: action.payload,
            featured_categories,
        }
    }

    if (action.type === GET_CATEGORY_ERROR) {
        return { ...state, categories_loading: false, categories_error: true }
    }

    if (action.type === GET_RECYCLE_CATEGORIES_BEGIN) {
        return { ...state, recycle_categories_loading: true }
    }

    if (action.type === GET_RECYCLE_CATEGORIES_SUCCESS) {
        return {
            ...state,
            recycle_categories_loading: false,
            recycle_categories: action.payload,
        }
    }

    if (action.type === GET_RECYCLE_CATEGORIES_ERROR) {
        return { ...state, recycle_categories_loading: false, recycle_categories_error: true }
    }

    if (action.type === ADD_TO_CATEGORY) {
        return { ...state, categories: [...state.categories, action.payload] }
    }

    if (action.type === ADD_TO_RECYCLE_CATEGORY) {
        return { ...state, recycle_categories: [...state.recycle_categories, action.payload] }
    }

    if (action.type === GET_VALUE_CATEGORY) {
        const { value, index } = action.payload

        return {
            ...state,
            value_category: value,
            index_category: index,
        }
    }

    if (action.type === UPDATE_CATEGORIES) {
        const { data, index } = action.payload
        const tempCategory = state.categories.map((item, ind) => index === ind ? data : item)

        return {
            ...state,
            categories: tempCategory,
            index_category: -1,
        }
    }

    if (action.type === REMOVE_CATEGORY_ITEM) {
        const tempCategory = state.categories.filter((item) => item.id !== action.payload)
        return { ...state, categories: tempCategory }
    }

    if (action.type === REMOVE_RECYCLE_CATEGORY_ITEM) {
        const tempCategory = state.recycle_categories.filter((item) => item.id !== action.payload)
        return { ...state, recycle_categories: tempCategory }
    }

    throw new Error(`No Matching "${action.type}" - action type`)
}

export default categories_reducer;