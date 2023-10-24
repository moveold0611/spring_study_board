import React from 'react';
import { useEffect } from 'react';
import { useQueryClient } from 'react-query';
import { Navigate, useSearchParams } from 'react-router-dom';

function SigninOauth2(props) {
    const [ searchParams, setSearchParams ] = useSearchParams();
    const queryClient = useQueryClient();
    localStorage.setItem("accessToken", "Bearar " + searchParams.get("token"));
    queryClient.refetchQueries(["getPrincipal"]);

    window.location.replace("/")
    return null;
}
export default SigninOauth2;
    
