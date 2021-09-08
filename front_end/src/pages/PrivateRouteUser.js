import React from 'react'
import { Route, Redirect } from 'react-router-dom';
import AuthService from '../service/Auth-service';

const PrivateRouteUser = ({ children, ...rest }) => {
  const current = AuthService.getCurrentUser();
  return (
    <Route
      {...rest}
      render={() => {
        return (current && current.role === 'ROLE_USER') ? children : <Redirect to='/login'></Redirect>
      }}
    ></Route>
  )
}
export default PrivateRouteUser;