import React, { useState } from 'react';
import RootContainer from '../../components/RootContainer/RootContainer';
import { useRef } from 'react';
import { updatePassword } from '../../apis/api/profile';

/** @jsxImportSource @emotion/react */

function EditPassword(props) {

        const [ passwordObj, setPasswordObj ] = useState({
            oldPassword: "",
            newPassword: "",
            checkNewPassword: ""
        }); 

    const handleInputChange = (e) => {
        console.log(passwordObj)
        const {name, value} = e.target;

        setPasswordObj({
            ...passwordObj,          
            [name]: value
        })
    }

    const handleUpdatePasswordSubmit = () => {
        try {
            const response = updatePassword(passwordObj);
        } catch (error) {
            console.log(error.response.data)
        }
    }

    return (
        <RootContainer>
            <div>
                <div><input type='password' name='oldPassword' onChange={handleInputChange} placeholder='기존 비밀번호' /></div>
                <div><input type='password' name='newPassword' onChange={handleInputChange} placeholder='새 비밀번호' /></div>
                <div><input type='password' name='checkNewPassword' onChange={handleInputChange} placeholder='새 비밀번호 확인' /></div>

                <div><button onClick={handleUpdatePasswordSubmit}>비밀번호 변경</button></div>
            </div>
        </RootContainer>
    );
}

export default EditPassword;