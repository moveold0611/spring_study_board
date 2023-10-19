import React from 'react';
import RootContainer from '../../components/RootContainer/RootContainer';
import { local } from 'styled-react/lib/base';
import { useQueryClient } from 'react-query';
import base64 from "base-64";
import { sendMail } from '../../apis/api/mail';

function Mypage(props) {
    const queryClient = useQueryClient();
    const principalState = queryClient.getQueryState("getPrincipal");
    const principal = principalState.data.data;
    

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

    return (
        <RootContainer>
            <div>
                <img src=''/>
            </div>
            <div>
                누적 포인트: 0원
            </div>
            <div>
                <div>닉네임: {nickname}</div>
                <div>이름:{name}</div>
                <div>이메일: {email} {principal.enabled ? "인증완료" : <button onClick={handleSendMail}>인증하기</button>}</div>
            </div>
        </RootContainer>
    );
}

export default Mypage;