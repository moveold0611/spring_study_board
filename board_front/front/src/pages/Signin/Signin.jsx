import React, { useState } from 'react';
import { useNavigate } from 'react-router';
import { signin } from '../../apis/api/account';

function Signin(props) {
    const navigate = useNavigate();

    const defaultUser = {
        email: "",
        password: ""
    }

    const [ signinUser, setSigninUser ] = useState(defaultUser);

    const handleSignupClick = () => {
        navigate("/auth/signup")
    }


    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setSigninUser({
            ...signinUser,
            [name]: value
        })
    }

    const handleSigninClick = async () => {
        try {         
        const response = await signin(signinUser);
        localStorage.setItem("accessToken" ,"Bearer " + response.data);
        window.location.replace("/");
        } catch (error) {
            if(error.response.status == 401) {
                alert(error.response.data.authError)
            }
        }
    }


    return (
        <div>
            <div>
                <input type='email' name='email' onChange={handleInputChange} placeholder='이메일' ></input>
                <input type='password' name='password' onChange={handleInputChange} placeholder='비밀번호' ></input>
                <div><button onClick={handleSigninClick}>로그인</button></div>
                <div><button onClick={handleSignupClick}>회원가입</button></div>
            </div>
        </div>
    );
}

export default Signin;