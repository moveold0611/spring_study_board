import React from 'react';
import { Route, Routes } from 'react-router';
import BoardWrite from "../../pages/BoardWrite/BoardWrite"

function BoardRoute(props) {
    return (
    <Routes>    
        <Route path='/:category' element={<></>}/>
        <Route path='/:category/register' element={<></>}/>
        <Route path='/:category/edit' element={<></>}/>
        <Route path='/write' element={<BoardWrite/>}/>
    </Routes>
    );
}

export default BoardRoute;