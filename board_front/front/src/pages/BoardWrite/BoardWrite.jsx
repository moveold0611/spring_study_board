import React, { useState } from 'react';
import RootContainer from '../../components/RootContainer/RootContainer';
import ReactQuill from 'react-quill';
import { useEffect } from 'react';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import Select from 'react-select';
import { addBoard, getCategory } from '../../apis/api/board';

const buttonContainer = css`
    display: flex;
    justify-content: flex-end;
    /* width: 100%; */
    align-items: center;
    margin-top: 5px;

    /* ql-container {
        min-height: 400px;
    } */
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

function BoardWrite(props) {
    const [ newCategory, setNewCategory ] = useState("");
    const [ selectOptions, setSelectOptions ] = useState({});
    const [ selectedOption, setSelectedOption ] = useState();


    useEffect(()=> {
        getCategory()
        .then((response) => {
            setSelectOptions(
                response.data.map(category => {return {
                    value: category.boardCategoryName,
                    label: category.boardCategoryName
                }})
            )
        })
    }, [])

    useEffect(() => {
        if(selectedOption == null) {
            setSelectedOption(selectOptions[0])
        }
    }, [selectOptions]);

    const [ content, setContent ] = useState({
        email: "",
        contentId: "",
        content: "",
        contentImg: ""
    });

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

    useEffect(() => {
        if(!!newCategory) {        
            const newOption = {value: newCategory, label: newCategory}
            setSelectedOption(newOption)

            if(!selectOptions.map(option => option.value).includes(newOption.value)) {
                setSelectOptions([
                    ...selectOptions,
                    newOption
                ]);
            }
        }        
    }, [newCategory]);

    const handleTitleChange = (e) => {
        console.log("change test")
    }

    const handleContentInput = (e) => {
        setContent(e);
    }

    const handleSelectChange = (option) => {
        setSelectedOption(option);
    }

    const handleAddCategoryClick = () => {
        const categoryName = window.prompt("새로 추가할 카테고리명을 입력하세요.");
        if(!categoryName) {
            return;
        }
        setNewCategory(categoryName);
    }

    const handleBoardSubmit = async () => {
        try {
            const response = await addBoard();
        } catch (error) {
            console.log(error.response.data);
        }
    }

    return (
        <RootContainer>
            <div>
                <h1>글쓰기</h1>
                <div css={categoryContainer}>
                    <div css={selectBox}>                        
                        <Select options={selectOptions} onChange={handleSelectChange} defaultValue={selectedOption} value={selectedOption}/>
                    </div>
                    <button onClick={handleAddCategoryClick}>카테고리 추가</button>
                </div>
                <div><input css={titleInput} type='text' name='title' placeholder='제목' onChange={handleTitleChange}/></div>
                <div>
                    <ReactQuill onChange={handleContentInput} style={{maxWidth: "928px", heigth: "500px"}} modules={modules}/>
                </div>
                <div>
                    <button onClick={handleBoardSubmit} css={buttonContainer}>작성하기</button>
                </div>
            </div>
        </RootContainer>
    );
}

export default BoardWrite;