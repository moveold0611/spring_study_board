import React from 'react';
/** @jsxImportSource @emotion/react */
import { Navigate, Route, Routes } from 'react-router';
import Signin from '../../pages/Signin/Signin';
import Signup from '../../pages/Signup/Signup';
import { useQueryClient } from 'react-query';

import SignupOauth2 from '../../pages/Signup/SignupOauth2';
import SignupOauth2Merge from '../../pages/Signup/SignupOauth2Merge';
import SigninOauth2 from '../../pages/Signin/SigninOauth2';


function AuthRoute(props) {
    const queryClient = useQueryClient();
    const principalState = queryClient.getQueryState("getPrincipal");
    
    if(!!principalState?.data?.data) {
        return <Navigate to={"/"}/>
    }

    return (
        <Routes>
            <Route path='signin' element={<Signin />}/>
            <Route path='signup' element={<Signup />}/>
            <Route path='signup/oauth2' element={<SignupOauth2 />}/>
            <Route path='signup/oauth2/merge' element={<SignupOauth2Merge/>}/>
            <Route path='oauth2/login' element={<SigninOauth2/>}/>
        </Routes>
    );
}

export default AuthRoute;