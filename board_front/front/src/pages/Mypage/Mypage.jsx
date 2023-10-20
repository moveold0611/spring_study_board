import React, { useEffect } from 'react';
import RootContainer from '../../components/RootContainer/RootContainer';
import { useQueryClient } from 'react-query';
import { sendMail } from '../../apis/api/mail';
import { css } from '@emotion/react'
import { useRef } from 'react';
/** @jsxImportSource @emotion/react */
import { ref, getDownloadURL, uploadBytes, uploadBytesResumable } from 'firebase/storage'
import { storage } from '../../apis/firebase/firebase'
import { useState } from 'react';
import { uploadProfileImg } from '../../apis/api/profile';
import { Line } from "rc-progress"
import { Link } from 'react-router-dom';

const infoHeader = css`
    display: flex;
    align-items: center;
    margin: 10px;
    border: 1px solid #dbdbdb;
    border-radius: 10px;
    padding: 20px;
    width: 100%;
`;

const imgBox = css`
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100px;
    height: 100px;
    overflow: hidden;
    border-radius: 50%;
    border: 1px solid #dbdbdb;
    margin-right: 20px;
    cursor: pointer;

    & > img {
        width: 100%;
    }
`;

const file = css`
  display: none;
`;

function Mypage(props) {
    const queryClient = useQueryClient();
    const principalState = queryClient.getQueryState("getPrincipal");
    const principal = principalState.data.data;
    const profileFileRef = useRef();
    const [ uploadFiles, setUploadFiles ] = useState([]);
    const [ profileImgSrc, setProfileImgSrc ] = useState("");
    const [ progressPercent, setProgressPercnet ] = useState(0);

    useEffect(() => {
        setProfileImgSrc(principal.profileUrl);
    }, [])

    const name = principal.name;
    
    const nickname = principal.nickname;
    const email = principal.email;

    const handleSendMail = async () => {
        try {
            await sendMail();
            alert("이메일 전송 완료. 메일함을 확인해주세요.");
        } catch (error) {
            alert("인증메일 전송 실패. 다시 시도해주세요.");
        }
    }

    const handleProfileUploadClick = () => {
        if(window.confirm("프로필 사진을 변경하시겠습니까?")) {
            profileFileRef.current.click();
        }
    }

    const handleProfileChange = (e) => {
        const files = e.target.files;
        console.log(files);

        if(!files.length) {
            setUploadFiles([]);
            e.target.value = ""
            return;
        }

        for(let file of files) {
            console.log("asdfasdfasdf");
            setUploadFiles([...uploadFiles, file]);
        }
        const reader = new FileReader();
        reader.onload = (e) => {
            setProfileImgSrc(e.target.result);
        }

        reader.readAsDataURL(files[0]);
    }

    const handleUploadCancel = () => {
        setUploadFiles([]);
        profileFileRef.current.value = "";
        console.log(uploadFiles)
    }

    const handleUploadSubmit = async () => {
            const storageRef = ref(storage, `files/profile/${uploadFiles[0].name}`)
            const uploadTask = uploadBytesResumable(storageRef, uploadFiles[0]);

            uploadTask.on(
                "state_changed",
                (snapshot) => {
                    setProgressPercnet(
                        Math.round((snapshot.bytesTransferred / snapshot.totalBytes) * 100));
                },
                (error) => {
                    console.error(error);
                },
                () => {
                    getDownloadURL(storageRef).then(downloadUrl => {   
                        try {
                            console.log(downloadUrl);
                            setProfileImgSrc(downloadUrl);
                            uploadProfileImg({profileImgUrl: downloadUrl}).then((response) => {
                                alert("프로필 사진이 변경되었습니다.")
                                window.location.reload();
                            })
                        } catch (error) {
                            console.log("프로필 업로드 실패")
                            console.log(error.response.data)
                        }
                    })   
                }
            );
            
    }

    return (
        <RootContainer>
            <div css={infoHeader}>
                <div>
                    <div css={imgBox} onClick={handleProfileUploadClick}>
                        <img src={profileImgSrc} alt=''/>
                    </div>
                    <input css={file} onChange={handleProfileChange} type="file" ref={profileFileRef} />
                    {!!uploadFiles.length &&
                    <div>
                        <Line percent={progressPercent} strokeWidth={4} strokeColor="#dbdbdb"/>
                        <button onClick={handleUploadSubmit}>변경</button>
                        <button onClick={handleUploadCancel}>취소</button>
                    </div>}
                    <div>
                        누적 포인트: 0원
                    </div>
                </div>
            </div>
            <div>
                
                <div>닉네임: {nickname}</div>
                <div>이름:{name}</div>
                <div>이메일: {email} {principal.enabled ? "인증완료" : <button onClick={handleSendMail}>인증하기</button>}</div>
                <Link to={"/account/password"}>비밀번호 변경</Link>
            </div>
        </RootContainer>
    );
}

export default Mypage;