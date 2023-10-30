import React, { useState } from 'react';
import { useNavigate } from 'react-router';
import { signup } from '../../apis/api/account';

function Signup(props) {
    const navigate = useNavigate();

    const user = {
        email: "",
        password: "",
        name: "",
        nickname: ""
    }

    const [ signupUser, setSignupUser ] = useState(user);

    const handleSigninClick = () => {
        navigate("/auth/signin")
    }

    const handleChange = (e) => {
        const { name, value } = e.target;
        setSignupUser({
            ...signupUser,
            [name]: value
        })
        console.log(signupUser)
    }


    const handleSignupSubmit = async () => {
        try {
            console.log(signupUser)
            const response = await signup(signupUser);
            console.log(response)
        } catch (error) {
            console.log(error)
        }
    }

    
    return (
        <div>
            <div><input type='email' name='email' placeholder='이메일' onChange={handleChange}></input></div>
            <div><input type='password' name='password' placeholder='비밀번호' onChange={handleChange}></input></div>
            <div><input type='text' name='name' placeholder='이름' onChange={handleChange}></input></div>
            <div><input type='text' name='nickname' placeholder='닉네임' onChange={handleChange}></input></div>
            <div><button onClick={handleSignupSubmit}>가입하기</button></div>
            <div><button onClick={handleSigninClick}>로그인</button></div>
        </div>
    );
}

export default Signup;