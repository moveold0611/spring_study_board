import React from 'react';
import { Route, Routes } from 'react-router';
import BoardWrite from "../../pages/BoardWrite/BoardWrite"
import BoardList from '../../pages/BoardList/BoardList';
import BoardDetails from '../../pages/BoardDetails/BoardDetails';
import BoardUpdate from '../../pages/BoardUpdate/BoardUpdate';

function BoardRoute(props) {
    return (
    <Routes>    
        <Route path='/:category/:page' element={<BoardList/>}/>
        <Route path='/:category/edit' element={<></>}/>
        <Route path='/:category' element={<></>}/>
        <Route path='/details/:boardId' element={<BoardDetails />}/>
        <Route path='/write' element={<BoardWrite/>}/>
        <Route path='/update/:boardId' element={<BoardUpdate/>}/>
    </Routes>
    );
}

export default BoardRoute;