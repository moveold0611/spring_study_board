import React from 'react';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import Sidebar from '../Sidebar/Sidebar';

const rootContainer = css`
    display: flex;
    width: 100%;
    min-height: 600px;
    height: 100%;
`

const mainContainer = css`
    flex-grow: 1;
    border: 1px solid #dbdbdb;
    border-radius: 10px;
    padding: 20px 20px 70px;
`;

function RootContainer({ children }) {
    return (
        <div css={rootContainer}>
            <Sidebar/>
            <div css={mainContainer}>
                {children}
            </div>
        </div>
    );
}

export default RootContainer;