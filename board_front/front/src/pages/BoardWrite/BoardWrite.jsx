import React, { useState } from 'react';
import RootContainer from '../../components/RootContainer/RootContainer';
import ReactQuill from 'react-quill';
import { useEffect } from 'react';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import Select from 'react-select';
import { addBoard, getCategory } from '../../apis/api/board';
import {  useQueryClient } from 'react-query';
import { useNavigate } from 'react-router';
import { pointUse } from '../../apis/api/point.js';

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

function BoardWrite(props) {
    const navigate = useNavigate();
    const [ newCategory, setNewCategory ] = useState("");
    const [ selectOptions, setSelectOptions ] = useState({});
    const [ selectedOption, setSelectedOption ] = useState();
    const queryClient = useQueryClient();
    const principal = queryClient.getQueryState("getPrincipal");


    const [ content, setContent ] = useState({
        title: "",
        content: "",
        categoryId: 0,
        categoryName: ""
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
        console.log(principal)
        if(!principal.data) {
            alert("로그인 후 게시글을 작성하세요.");
            window.location.replace("/");
            return;
        }

        if(!principal?.data?.data.enabled) {
            alert("사용자 인증 후 게시글을 작성할 수 있습니다.")
            window.location.replace("/")
            return;
        }
    }, [])

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
            const newOption = {value: 0, label: newCategory}
            setSelectedOption(newOption)

            if(!selectOptions.map(option => option.label).includes(newOption.label)) {
                setSelectOptions([
                    ...selectOptions,
                    newOption
                ]);
            }
        }        
    }, [newCategory]);

    const handleTitleChange = (e) => {
        setContent({
            ...content,
            title: e.target.value
        })
    }

    const handleContentInput = (value) => {
        setContent({
            ...content,
            content: value
        });
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

    const handleWriteSubmit = async () => {
        queryClient.refetchQueries(["getPrincipal"]);
        const point =principal?.data?.data?.userPoint;
        if(point >= 500) {
            if(window.confirm("작성 시 포인트가 500 차감됩니다. 작성하시겠습니까?")) {
                try {
                    const pointUseData = ({
                        email: principal?.data?.data?.email,
                        pointHistoryPrice: 500
                    });
                    await addBoard(content);
                    await pointUse(pointUseData);
                    window.location.replace("/board/all/1")
                } catch (error) {
                    console.log(error.response.data);
                }
            }else {
                return;
            }
        }else {
            if(window.confirm("포인트가 부족합니다. 충전하시겠습니끼?")) {
                navigate("/store/products")
            }else {
                return;
            }
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
                    <button onClick={handleWriteSubmit} css={buttonContainer}>작성하기</button>
                </div>
            </div>
        </RootContainer>
    );
}

export default BoardWrite;