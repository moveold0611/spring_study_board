import React from 'react';
import { useQueryClient } from 'react-query';
import { Navigate, Route, Routes } from 'react-router';
import Mypage from '../../pages/Mypage/Mypage';

function AccountRoute(props) {
    const queryClient = useQueryClient();
    const principalState = queryClient.getQueryState("getPrincipal");
    
    if(!principalState?.data?.data) {
        alert("로그인이 필요한 페이지입니다.")
        return <Navigate to={"/auth/signin"}/>
    }

    return (
        <Routes>
            <Route path='/mypage' element={<Mypage />}/>
            <Route path='/password' element={<></>}/>
        </Routes>
    );
}

export default AccountRoute;