import React from 'react';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { Link } from 'react-router-dom';

const header = css`
    display: flex;
    justify-content: center;
    align-items: center;
    border: 1px solid #dbdbdb;
    border-radius: 10px;
    margin: 20px 0px;
    width: 100%;
    height: 80px;
    cursor: pointer;

    & > a {
        color: black;
        text-decoration: none;
    }
`;

function Header(props) {
    return (
        <div css={header}>
            <Link to={"/"}> <h1>게시판 프로젝트</h1></Link>
        </div>
    );
}

export default Header;