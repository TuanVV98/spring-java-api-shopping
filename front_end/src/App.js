import React from 'react'
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'
import About from './components/About';
import Categories from './components/admin/Categories';
import Products from './components/admin/Products';
import RecycleCategories from './components/admin/RecycleCategories';
import RecycleProducts from './components/admin/RecycleProducts';
import TableProducts from './components/admin/TabaleProducts';
import TableCategories from './components/admin/TableCategories';
import TableOrder from './components/admin/TableOrder';
import TableUser from './components/admin/TableUser';
import Login from './components/auth/Login';
import Register from './components/auth/Register';
import RegisterProfile from './components/auth/RegisterProfile';
import OrderForm from './components/carts/OrderForm';
import Navbar from "./components/headers/Navbar";
import Hero from './components/Hero';
import CartPage from './pages/CartPage';
import CheckOut from './pages/CheckOut';
import PrivateRouteAdmin from './pages/PrivateRouteAdmin';
import PrivateRouteUser from './pages/PrivateRouteUser';
import ProductsPage from './pages/ProductsPage';
import SingleProductPage from './pages/SingleProductPage';


function App() {
  return (
    <React.Fragment>

      <Router>
        <Navbar />
        <Switch>
          <Route exact path='/'>
            <Hero />
          </Route>
          <Route exact path='/about'>
            <About />
          </Route>
          <Route exact path='/login' >
            <Login />
          </Route>
          <Route exact path='/register' >
            <Register />
          </Route>
          <Route exact path='/cart' >
            <CartPage />
          </Route>
          <Route exact path='/products'>
            <ProductsPage />
          </Route>
          <Route exact path='/products/:model' children={<SingleProductPage />} />
          <Route exact path='/user/profile' >
            <RegisterProfile />
          </Route>
          <PrivateRouteAdmin exact path='/admin/add/products'>
            <Products />
          </PrivateRouteAdmin>
          <PrivateRouteAdmin exact path='/admin/table/products'>
            <TableProducts />
          </PrivateRouteAdmin>
          <PrivateRouteAdmin exact path='/admin/recycle/products'>
            <RecycleProducts />
          </PrivateRouteAdmin>
          <PrivateRouteAdmin exact path='/admin/table/categories'>
            <TableCategories />
          </PrivateRouteAdmin>
          <PrivateRouteAdmin exact path='/admin/add/categories'>
            <Categories />
          </PrivateRouteAdmin>
          <PrivateRouteAdmin exact path='/admin/recycle/categories'>
            <RecycleCategories />
          </PrivateRouteAdmin>
          <PrivateRouteAdmin exact path='/admin/table/orders'>
            <TableOrder />
          </PrivateRouteAdmin>
          <PrivateRouteAdmin exact path='/admin/table/users'>
            <TableUser/>
          </PrivateRouteAdmin>
          <PrivateRouteUser exact path='/checkout'>
            <CheckOut/>
          </PrivateRouteUser>
        </Switch>
      </Router>
      {/* <TableOrderDetails/> */}
      <OrderForm />
    </React.Fragment>
  );
}

export default App;
