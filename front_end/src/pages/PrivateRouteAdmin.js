import React from 'react'
import { Route, Redirect } from 'react-router-dom';
import AuthService from '../service/Auth-service';

const PrivateRouteAdmin = ({ children, ...rest }) => {

  const current = AuthService.getCurrentUser();
 
 
  return (
    <Route
      {...rest}
      render={() => {
        return (current && current.role === 'ROLE_ADMIN') ? children : <Redirect to='/login'></Redirect>
      }}
    ></Route>
  )
}
export default PrivateRouteAdmin