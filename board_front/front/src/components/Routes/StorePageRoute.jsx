import React from 'react';
import { Route, Routes } from 'react-router';
import StorePage from '../../pages/StorePage/StorePage';

function StorePageRoute(props) {
    return (
        <Routes>
            <Route path='/products' element={<StorePage/>}/>
        </Routes>
    );
}

export default StorePageRoute;