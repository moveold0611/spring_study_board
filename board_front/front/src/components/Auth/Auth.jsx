import React from 'react';
/** @jsxImportSource @emotion/react */
import { Route, Routes } from 'react-router';
import Signin from '../../pages/Signin/Signin';
import Signup from '../../pages/Signup/Signup';


function Auth(props) {
    return (
        <Routes>
            <Route path='/signin' element={<Signin />}/>
            <Route path='/signup' element={<Signup />}/>
        </Routes>
    );
}

export default Auth;