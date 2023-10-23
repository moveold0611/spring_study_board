import React from 'react';
import { Route, Routes } from 'react-router';

function BoardRoute(props) {
    return (
    <Routes>    
        <Route path='/:category' element={<></>}/>
        <Route path='/:category/register' element={<></>}/>
        <Route path='/:category/edit' element={<></>}/>
    </Routes>
    );
}

export default BoardRoute;