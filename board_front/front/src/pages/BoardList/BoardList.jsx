import React, { useState } from 'react';
import { useLocation, useNavigate, useParams } from 'react-router';
import RootContainer from '../../components/RootContainer/RootContainer';
import ReactSelect from 'react-select';
import { Link } from 'react-router-dom';
import { useEffect } from 'react';
import { getBoardCount, getBoards } from '../../apis/api/board';
import { useQuery } from 'react-query';
/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react';

const table = css`
    width: 100%;
    border-collapse: collapse;
    border: 1px solid #dbdbdb;

    & th, td {
        border: 1px solid #dbdbdb;
        height: 30px;
        text-align: center;
    }

    & td {
        cursor: pointer;
    }
`



const searchContainer = css`
    display: flex;
    justify-content: flex-end;
    margin-bottom: 10px;
    width: 100%;

    & > * {
        margin-left: 5px;
    }
`;



const selectBox = css`
    width: 100px;
`;



const SPageNumbers = css`
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-top: 10px;
    width: 300px;

    & button {
        display: flex;
        justify-content: center;
        align-items: center;
        margin: 0px 3px;
        width: 20px;
        border: 1px solid #dbdbdb;
        cursor: pointer;
    }
`;


const SBoardTitle = css`
    width: 500px;
    max-width: 500px;
    overflow: hidden;
    text-overflow: ellipsis; // 텍스트 넘어갈 시 ...처리
    white-space: nowrap; // 줄바꿈 금지
`;

// ============================================================================================================

function BoardList(props) {

// =================== 변수, 상태 ========================

    const options = [
        {value: "전체", label: "전체"},
        {value: "제목", label: "제목"},
        {value: "작성자", label: "작성자"}
    ];

    const defaultSearchParams = {
        optionName : options[0].label,
        searchValue: "",
    }

    const navigate = useNavigate();
    const { category, page } = useParams();
    const [ searchParams, setSearchParams ] = useState(defaultSearchParams);


// ===================== 랜더링 ===================

    const getBoardList = useQuery(["getBoardList", page, category], async () => {
        const option = {    
            params: {
                optionName: searchParams.optionName,
                searchValue: searchParams.searchValue
        }}
        return await getBoards(category, page, option);
    }, {
        refetchOnWindowFocus: false
    })

    const getBoardCountQuery = useQuery(["getBoardCount", page, category], async () => {
        const option = {    
            params: {
                optionName: searchParams.optionName,
                searchValue: searchParams.searchValue
        }}
        return await getBoardCount(category, option);       
    }, {
        refetchOnWindowFocus: false
    });


// ==================== 핸들, 함수 =========================

    const handleSearchOptionChange = (options) => {
        console.log(options)
        setSearchParams({
            ...searchParams,
            optionName: options.label
        })
    }


    const handleSearchChange = (e) => {
        setSearchParams({
            ...searchParams,
            searchValue: e.target.value
        })
    }


    const handleSearchClick = () => {
        console.log(searchParams)
        navigate(`/board/${category}/1`);
        getBoardList.refetch();
        getBoardCountQuery.refetch();
    }


    const handlePageChangeByNum = (category, num) => {
        navigate(`/board/${category}/${num}`);
    }
    const handlePageChangeByArrowMinus = (category, page) => {
        navigate(`/board/${category}/${parseInt(page) - 1}`);
    }
    const handlePageChangeByArrowPlus = (category, page) => {
        navigate(`/board/${category}/${parseInt(page) + 1}`);
    }

    const pagination = () => {
        if(getBoardCount.isLoading) {
            return <></>
        }

        const totalBoardCount = getBoardCountQuery.data;

        const lastPage = totalBoardCount % 10 === 0
            ? totalBoardCount / 10 : Math.floor(totalBoardCount / 10) + 1;

        const startIndex = parseInt(page) % 5 === 0
            ? parseInt(page) - 4 : parseInt(page) - (parseInt(page) % 5) + 1;

        const endIndex = startIndex + 4 <= lastPage 
            ? startIndex + 4 : lastPage;

        const pageNumbers = [];
        for(let i = startIndex; i <= endIndex; i++) {
            pageNumbers.push(i);
        }

        return (
            <>
                <button disabled={parseInt(page) === 1} onClick={() => handlePageChangeByArrowMinus(category, page)}>&#60;</button>

                {pageNumbers.map(num => {
                    return <button onClick={()=>handlePageChangeByNum(category, num)} key={num}>{num}</button>
                })}

                <button disabled={parseInt(page) === lastPage} onClick={() => handlePageChangeByArrowPlus(category, page)}>&#62;</button>
            </>
        )
    }


    const handleNavigateBoardDetails = (boardId) => {
        navigate(`/board/details/${boardId}`)
    }

// ====================================================================================
    return (
        <RootContainer>
            <div>
                <h1>{category === "all" ? "전체 게시글" : category}</h1>

                <div css={searchContainer}>
                    <div css={selectBox}>
                        <ReactSelect onChange={handleSearchOptionChange}
                        options={options}
                        defaultValue={options[0]}/>
                    </div>
                    <input onChange={handleSearchChange}
                            type='text'/>
                    <button onClick={handleSearchClick}>검색</button>
                </div>

                <table css={table}>
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                            <th>추천</th>
                            <th>조회수</th>
                        </tr>
                    </thead>
                    <tbody>
                        {!getBoardList.isLoading && getBoardList?.data?.data.map(board => {
                            return  <tr onClick={() => {handleNavigateBoardDetails(board.boardId)}}
                                        key={board.boardId}>
                                        <td>{board.boardId}</td>
                                        <td css={SBoardTitle}>{board.title}</td>
                                        <td>{board.nickname}</td>
                                        <td>{board.createDate}</td>
                                        <td>{board.likeCount}</td>
                                        <td>{board.hitsCount}</td>
                                    </tr>
                        })}
                    </tbody>
                </table>

                <ul css={SPageNumbers}>
                        {pagination()}
                </ul>
            </div>
        </RootContainer>
    );
}

export default BoardList;