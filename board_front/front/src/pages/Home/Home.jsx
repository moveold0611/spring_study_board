/** @jsxImportSource @emotion/react */
import React from 'react';
import RootContainer from '../../components/RootContainer/RootContainer';

function Home({ children }) {
    return (
        <RootContainer>
            {children}
        </RootContainer>
    );
}

export default Home;