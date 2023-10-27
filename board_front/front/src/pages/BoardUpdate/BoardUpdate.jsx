import React, { useEffect } from 'react';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import RootContainer from '../../components/RootContainer/RootContainer';
import ReactQuill from 'react-quill';
import Select from 'react-select';
import { useState } from 'react';
import { getBoardDetails, getCategory, updateBoard } from '../../apis/api/board';
import { useQuery, useQueryClient } from 'react-query';
import { useParams } from 'react-router';


const buttonContainer = css`
    display: flex;
    justify-content: flex-end;
    align-items: center;
    margin-top: 5px;
`;

const categoryContainer = css`
    display: flex;
    margin-bottom: 10px;
`;

const selectBox = css`
    flex-grow: 1;    
    margin-right: 5px;
`;

const titleInput = css`
    width: 100%;
    height: 50%;
    margin-bottom: 10px;
`;


function BoardUpdate(props) {
    const [ selectedOption, setSelectedOption ] = useState();
    const [ selectOptions, setSelectOptions ] = useState({});
    const queryClient = useQueryClient();
    const principal = queryClient.getQueryState("getPrincipal");
    const { boardId } = useParams();

    const modules = {
        toolbar: {  
            container: [
                ["image"],
                [{header: [1, 2, 3, false]}],
                ["bold", "underline"],
                ["image"]
            ]
        }
    }

    const [ content, setContent ] = useState({
        boardTitle: "",
        boardContent: "",
        categoryId: 0,
        categoryName: "",
        boardId: parseInt(boardId)
    });

    useEffect(()=> {
        getCategory()
        .then((response) => {
            setSelectOptions(
                response.data.map(category => {return {
                    value: category.boardCategoryId,
                    label: category.boardCategoryName
                }})
            )
        })
        
        const principal = queryClient.getQueryState("getPrincipal");
        if(!principal.data) {
            alert("잘못된 접근입니다.");
            window.location.replace("/");
            return;
        }

        if(!principal?.data?.data.email) {
            alert("수정 권한이 없습니다.")
            window.location.replace("/")
            return;
        }
    }, [])


    const getBoardDetail = useQuery(["getBoardDetail"], async () => {
        return await getBoardDetails(boardId);
    }, {
        refetchOnWindowFocus: false,
        retry: 0,
        onSuccess: response => {
            console.log(response?.data)
            console.log(response?.data?.boardCategoryId)
            setSelectedOption({
                value: response?.data?.boardCategoryId,
                label: response?.data?.boardCategoryId
            })
        }
    })
    


    useEffect(() => {
        if(selectedOption == null) {
            setSelectedOption(selectOptions[0])
        }
    }, [selectOptions]);

    useEffect(() => {
        setContent({
            ...content,
            categoryId: selectedOption?.value,
            categoryName: selectedOption?.label
        })
    }, [selectedOption])



    const handleUpdateSubmit = async () => {
        try {        
            await updateBoard(content);
            window.location.back();   
        } catch (error) {
            
        }
    }



    const handleContentChange = (e) => {        
        setContent({
            ...content,
            boardTitle: e.target.value
        })
    }

    const handleContentInput = (value) => {
        setContent({
            ...content,
            boardContent: value
        })
    }

    const handleSelectChange = (option) => {
        setSelectedOption(option);
    }

    if(getBoardDetail.isLoading) {
        return <></> 
    }
    
    
    
    return (
        <RootContainer>
        <div>
            <h1>수정하기</h1>
            <div css={categoryContainer}>
                <div css={selectBox}>                        
                    <Select options={selectOptions} onChange={handleSelectChange} defaultValue={selectedOption} value={selectedOption}/>
                </div>
            </div>
            <div><input onChange={handleContentChange} css={titleInput} type='text' name='title' defaultValue={getBoardDetail?.data?.data?.boardTitle} placeholder='제목' /></div>
            <div>
                <ReactQuill defaultValue={getBoardDetail?.data?.data?.boardContent} onChange={handleContentInput} style={{maxWidth: "928px", heigth: "500px"}} modules={modules}/>
            </div>
            <div>
                <button onClick={handleUpdateSubmit} css={buttonContainer}>수정하기</button>
            </div>
        </div>
    </RootContainer>
    );
}

export default BoardUpdate;