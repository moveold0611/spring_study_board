import React from 'react';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { useNavigate } from 'react-router';
import { Link } from 'react-router-dom';
import { useQueryClient } from 'react-query';
import { useState } from 'react';
import { useEffect } from 'react';
import { getCategory } from '../../apis/api/board';

const layout = css`
    width: 320px;
    margin-right: 10px;
    min-height: 400px;
`;

const container = css`
    display: flex;
    flex-direction: column;
    align-items: center;
    border: 1px solid #dbdbdb;
    border-radius: 10px;
    padding: 20px;
`;

function Sidebar(props) {
    const navigate = useNavigate()
    const queryClient = useQueryClient();
    const principal = queryClient.getQueryData("getPrincipal");
    const principalStatue = queryClient.getQueryState("getPrincipal");
    const [ categories, setCategories ] = useState([]);

    useEffect(() => {
        getCategory()
        .then((response) => {
            setCategories(response.data)
        })
    }, [])

    const handleSigninClick = () => {
        navigate("/auth/signin")
    }

    const handleSignoutClick = () => {
        console.log(principal);
        console.log(principalStatue);
        localStorage.removeItem("accessToken");
        window.location.replace("/"); // react의 상태 초기화, naivgate는 유지
    }

    return (
        <div css={layout}>
            {!!principalStatue?.data?.data ? (
            <div css={container}>
                <h3>{principalStatue.data.data.nickname}님 환영합니다</h3>
                <div>
                    <button onClick={handleSignoutClick}>로그아웃</button>
                </div>
                <div>
                    <Link to={"/account/mypage"}>마이페이지</Link>
                </div>
            </div>
            ) : (
            <div css={container}>
                <h3>로그인 후 게시판을 이용해보세요</h3>
                <div>
                    <button onClick={handleSigninClick}>로그인</button>
                </div>
                <div>
                    <Link to={"/auth/forgot/password"}>비밀번호 찾기</Link>
                    <Link to={"/auth/signup"}>회원가입</Link>
                </div>
            </div>
            )}
            <div>
                <ul>
                    <Link to={"/board/write"}>글쓰기</Link>
                    <Link to={"/board/all/1"}><li>전체 게시글({categories.map(category => category.boardCount).reduce((sum, curValue) => sum + curValue, 0)})</li></Link>
                    {categories.map(category => {
                            return <Link key={category.boardCategoryId} to={`/board/${category.boardCategoryName}/1`} reloadDocument={true}>
                                    <li>
                                        {category.boardCategoryName} ({category.boardCount})
                                    </li>
                                </Link>;
                    })}

                </ul>                
            </div>
        </div>
    );
}

export default Sidebar;