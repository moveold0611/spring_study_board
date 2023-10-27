import React, { useState } from 'react';
import RootContainer from '../../components/RootContainer/RootContainer';
import { deleteBoardLike, getBoardDetails, getLikeStateReq, insertBoardLike, removeBoard, updateBoard } from '../../apis/api/board';
import { useQuery, useQueryClient } from 'react-query';
import { Navigate, useNavigate, useParams } from 'react-router';
/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react';


const boardContainer = css`
    position: relative;
    width: 928px;
    word-wrap: break-word; // 자동 줄바꿈(단어 줄바꿈)
`;

const line = css`
    width: 100%;
    margin: 40px 0px;
    border-bottom: 2px solid #dbdbdb;
`;

const contentContainer = css`
    width: 100%;
    & img {
        max-width: 100%;
    }
`;



const SSideOption = css`
    position: absolute;
    right: -80px;
    height: 100%;
`;

const SLikeButton = (isLike) => css`
    position: sticky;
    top: 150px;
    width: 50px;
    height: 50px;
    border-radius: 50%;
    border: 1px solid #dbdbdb;
    background-color: ${isLike ? "#32a7dd" : "#fff"};
    cursor: pointer;
`;

const SBoardTitleContainer = css`    
    display: flex;
    justify-content: space-around;
    align-items: center;
`

const SBoardTitle = css`
    width: 50%;
    font-size: 40px;
`;

const STitleButton = css`
    height: 100px;
    width: 70px;
    
`

function BoardDetails(props) {
    const navigate = useNavigate();
    const { boardId } = useParams();
    const [ board, setBoard ] = useState();
    const queryClient = useQueryClient();
    const principal = queryClient.getQueryState("getPrincipal");

    const getBoardDetail = useQuery(["getBoardDetail"], async () => {
        try {
            return await getBoardDetails(boardId);    
        } catch (error) {            
            console.log(error.response.data)
        }        
    },
    {
        refetchOnWindowFocus: false,
        onSuccess: response => {
            setBoard(response.data)
        },
    })

    const getLikeState = useQuery(["getLikeState"], async () => {
        try {
            return await getLikeStateReq(boardId);    
        } catch (error) {            
            console.log(error.response.data)
        } 
        }, {
            retry: 0,
            refetchOnWindowFocus: false,
        }
    )

    if(getBoardDetail.isLoading) {
        return <></>
    }

    const handleLikeButtonClick = async () => {
        try {
            if(!!getLikeState?.data.data) {
                await deleteBoardLike(boardId);
            }else {
                await insertBoardLike(boardId);
            }
            getLikeState.refetch();
            console.log(getLikeState)
        } catch (error) {
            console.log(error.response.data)
        }
    }

    const handleUpdateSubmit = async (board) => {
        const boardId = board.boardId;
        navigate(`/board/update/${boardId}`)
        // await updateBoard();
    }
    const handleDeleteSubmit = async (board) => {
        if(!!window.confirm("정말로 삭제하시겠습니까?")) {
            try {
                await removeBoard(board.boardId);
                alert("게시글이 삭제되었습니다.")
                window.location.replace("/board/all/1")
            } catch (error) {
                console.log(error.response.data)
            }
        }else {
            return;
        }
    }

    return (
        <RootContainer>
            <div css={boardContainer}>
                <div css={SSideOption}>
                    {!getLikeState.isLoading && 
                        <button onClick={handleLikeButtonClick}
                        disabled={!principal?.data?.data}
                        css={SLikeButton(!!getLikeState?.data?.data)}>
                            <div>♥</div>
                            <div>10</div>
                        </button>
                    }
                </div>
                <div css={SBoardTitleContainer}>
                <h1 css={SBoardTitle}>{board.boardTitle}</h1>
                    <p><b>{board.nickname} - {board.createDate}</b></p>                
                    {board.email == principal?.data?.data?.email ? <>
                    <button onClick={() => handleUpdateSubmit(board)} css={STitleButton}>수정</button>
                    <button onClick={() => handleDeleteSubmit(board)} css={STitleButton}>삭제</button></> : <></>}
                </div>
                <div css={line}/>
                <div css={contentContainer} dangerouslySetInnerHTML={{__html: board.boardContent}} /> 
            </div>
        </RootContainer>
    );
}


export default BoardDetails;

