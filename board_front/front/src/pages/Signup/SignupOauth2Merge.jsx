import React from 'react';
import { useState } from 'react';
import { connectOldUserOfOauth2 } from '../../apis/api/profile';
import { useSearchParams } from 'react-router-dom';


function SignupOauth2Merge(props) {
    const [ searchParams, setSearchParams ] = useSearchParams();
    const [ mergeUser, setMergeUser ] = useState({
        email: searchParams.get("email"),
        password: "",
        oauth2Id: searchParams.get("oauth2Id"),
        provider: searchParams.get("provider")
    });

    const handleInputChange = (e) => {
        const { name,value } = e.target;
        setMergeUser({
            ...mergeUser,
            [ name ]: value
        })
    }

    const handleMergeSubmit = async () => {
        try {
            await connectOldUserOfOauth2(mergeUser);
            window.location.replace("/auth/signin");
        } catch (error) {            
            alert(error.response.data);
        }
    }

    return (
        <div>
            <h1>{searchParams.get("email")} 계정과 {searchParams.get("provider")} 계정을 통합하시려면 가입된 사용자의 비밀번호를 입력하세요.</h1>
            <div><input type='password' name='password' placeholder='비밀번호' onChange={handleInputChange}/></div>
            <button onClick={handleMergeSubmit}>확인</button>
        </div>
    );
}

export default SignupOauth2Merge;