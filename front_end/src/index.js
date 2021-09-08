import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import { ProductsProvider } from './context/product_context';
import { CategoriesProvider } from './context/categories_context';
import { FilterProvider } from './context/filters_context';
import { CartProvider } from './context/cart_context';
import { UserProvider } from './context/user_context';
import { AppProvider } from './context/app_context';
import { OrdersProvider } from './context/order_context';
ReactDOM.render(
  <AppProvider>
    <UserProvider>
      <CategoriesProvider>
        <ProductsProvider>
          <FilterProvider >
            <CartProvider>
              <OrdersProvider>
                <App />
              </OrdersProvider>
            </CartProvider>
          </FilterProvider >
        </ProductsProvider>
      </CategoriesProvider>
    </UserProvider>
  </AppProvider>,

  document.getElementById('root')
);


