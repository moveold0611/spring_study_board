import React from 'react';
import { Route, Routes } from 'react-router';
import BoardWrite from "../../pages/BoardWrite/BoardWrite"
import BoardList from '../../pages/BoardList/BoardList';

function BoardRoute(props) {
    return (
    <Routes>    
        <Route path='/:category/:page' element={<BoardList/>}/>
        <Route path='/:category/edit' element={<></>}/>
        <Route path='/:category' element={<></>}/>
        <Route path='/write' element={<BoardWrite/>}/>
    </Routes>
    );
}

export default BoardRoute;