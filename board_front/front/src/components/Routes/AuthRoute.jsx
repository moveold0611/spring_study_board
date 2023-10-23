import React from 'react';
/** @jsxImportSource @emotion/react */
import { Navigate, Route, Routes } from 'react-router';
import Signin from '../../pages/Signin/Signin';
import Signup from '../../pages/Signup/Signup';
import { useQueryClient } from 'react-query';
import SignupOauth2 from '../../pages/Signup/Signup_Oauth2';


function AuthRoute(props) {
    const queryClient = useQueryClient();
    const principalState = queryClient.getQueryState("getPrincipal");
    
    if(!!principalState?.data?.data) {
        return <Navigate to={"/"}/>
    }

    return (
        <Routes>
            <Route path='/signin' element={<Signin />}/>
            <Route path='/signup' element={<Signup />}/>
            <Route path='/signup/oauth2/*' element={<SignupOauth2 />}/>
        </Routes>
    );
}

export default AuthRoute;