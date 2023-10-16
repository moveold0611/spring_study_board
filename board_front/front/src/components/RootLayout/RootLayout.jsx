import React from 'react';
import Header from '../Header/Header';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";

const layout = css`
    width: 1300px;
    margin: 20px auto;
`;

function RootLayout({ children }) {
    return (
        <div css={layout}>
            <Header />
            {children}
        </div>
    );
}

export default RootLayout;