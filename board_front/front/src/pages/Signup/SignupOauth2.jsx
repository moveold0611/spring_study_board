import React from 'react';
import { Navigate, useNavigate } from 'react-router';
import { signup } from '../../apis/api/account';
import { useSearchParams } from 'react-router-dom';
import { useState } from 'react';
import { useEffect } from 'react';

function SignupOauth2(props) {
    const [ searchParams, setSearchParams ] = useSearchParams();
    const navigate = useNavigate();

    useEffect(()=> {
        if(!window.confirm("등록되지 않은 간편로그인 사용자입니다. 회원등록 하시겠습니까?")) {   
            window.location.replace("/auth/signin");
        }
    }, [])


    const name = searchParams.get("name");
    const profileImg = searchParams.get("profileImg");
    const oauth2Id = searchParams.get("oauth2Id");
    const provider = searchParams.get("provider");

    const user = {
        email: "",
        password: "",
        name: name,
        nickname: "",
        oauth2Id: oauth2Id,
        profileImg: profileImg,
        provider: provider
    }

    const [ signupUser, setSignupUser ] = useState(user);


    const handleSigninClick = () => {
        navigate("/auth/signin")
    }

    const handleChange = (e) => {
        console.log(provider)
        const { name, value } = e.target;
        setSignupUser({
            ...signupUser,
            [name]: value
        })
        console.log(signupUser)
    }
    

    const handleSignupSubmit = async () => {
        try {            
            await signup(signupUser);            
            alert("회원가입 완료");
            window.location.replace("/auth/signin");
        } catch (error) {
            console.log(error);
            if(Object.keys(error.response.data).includes("message")) {
                // 중복된 이메일이 있다면 계정 통합 권유
                if(window.confirm(`해당 이메일로 가입된 계정이 있습니다.\n ${signupUser.provider} 계정을 연동하시겠습니까?`)) {
                        navigate(`/auth/signup/oauth2/merge?oauth2Id=${signupUser.oauth2Id}&email=${signupUser.email}&provider=${signupUser.provider}`);
                }
            }else if(Object.keys(error.response.data).includes("nickname")) {
                alert("이미 사용중인 닉네입입니다. 다시 입력하세요.");
            }
        }
    }

    return (
        <div>
            <div><input type='email' name='email' placeholder='이메일' onChange={handleChange}></input></div>
            <div><input type='password' name='password' placeholder='비밀번호' onChange={handleChange}></input></div>
            <div><input type='text' name='name' placeholder='이름' value={signupUser.name} disabled={true}></input></div>
            <div><input type='text' name='nickname' placeholder='닉네임' onChange={handleChange}></input></div>
            <div><button onClick={handleSignupSubmit}>가입하기</button></div>
            <div><button onClick={handleSigninClick}>로그인</button></div>
        </div>
    );
}

export default SignupOauth2;