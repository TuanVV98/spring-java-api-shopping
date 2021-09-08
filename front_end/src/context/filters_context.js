import React, { useEffect, useContext, useReducer } from 'react'
import reducer from '../reducers/filter_reducer'
import { useProductsContext } from '../context/product_context';
import {
    LOAD_PRODUCTS,
    UPDATE_SORT,
    SORT_PRODUCTS,
    UPDATE_FILTERS,
    FILTER_PRODUCTS,
    CLEAR_FILTERS,
} from '../actions'

const initialState = {
    filtered_products: [],
    // all_products: [],
    grid_view: true,
    sort: 'price-lowest',
    filters: {
        text: '',
        category: 'all',
        min_price: 0,
        max_price: 0,
        price: 0,
    },
}


const FilterContext = React.createContext()

export const FilterProvider = ({ children }) => {

    const { products } = useProductsContext();
    const [state, dispatch] = useReducer(reducer, initialState)

    useEffect(() => {
        dispatch({ type: LOAD_PRODUCTS, payload: products })
    }, [products])

    useEffect(() => {
        dispatch({ type: FILTER_PRODUCTS })
        dispatch({ type: SORT_PRODUCTS })
    }, [products, state.sort, state.filters])

    const updateSort = (e) => {
        const value = e.target.value;
        dispatch({ type: UPDATE_SORT, payload: value })
    }

    const updateFilters = (e) => {

        let name = e.target.name
        let value = e.target.value
        // let value = e.target.index
        console.log(name)
        console.log(value)

        if (name === 'category') {
            value = e.target.textContent
        }

        if(name === 'price'){
            value = Number(value)
        }

        dispatch({ type: UPDATE_FILTERS, payload: { name, value } })
    }
    
    const clearFilters = () => {
        dispatch({ type: CLEAR_FILTERS })
    }
    return (
        <FilterContext.Provider
            value={{
                ...state,
                updateSort,
                updateFilters
            }}
        >

            {children}
        </FilterContext.Provider>
    )
}

export const useFilterContext = () => {
    return useContext(FilterContext)
}