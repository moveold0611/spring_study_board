import React, { useState } from 'react';
import RootContainer from '../../components/RootContainer/RootContainer';
/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react';
import { QueryClient, useQuery, useQueryClient } from 'react-query';
import { getProductsByServer } from '../../apis/api/store';
import { useEffect } from 'react';
import { orderToServer } from '../../apis/api/order';
import { useNavigate } from 'react-router';

const SStoreContainer = css`
    display: flex;
    justify-content: space-around;
    flex-wrap: wrap;
    border: 1px solid #dbdbdb;
    border-radius: 10px;
    padding: 20px;
    width: 100%;    
`;

const SProductContainer = css`
    margin: 10px;
    width: 30%;
    height: 200px;
    background-color: white;
    cursor: pointer;
`

function StorePage(props) {
    const navigate = useNavigate();

    // 결제 정보 가져오기
    const queryClient = useQueryClient();
    const getProducts = useQuery(["getProducts"], async ()=> {
        try {
            return await getProductsByServer();
        } catch (error) {
            
        }
    },{
        refetchOnWindowFocus: false,
        retry: 0,
    })

    

    useEffect(() => {
        const  iamport = document.createElement("script");
        iamport.src = "https://cdn.iamport.kr/v1/iamport.js"    
        document.head.appendChild(iamport);
        return () => {
            document.head.removeChild(iamport);
        }
    }, [])  

    const handlePaymentSubmit = (product) => {
        const principal = queryClient.getQueryState("getPrincipal");
        if(!window.IMP) {return;}
        const { IMP } = window;
        IMP.init("imp60574757");

        const paymentData = {
            pg: "kakaopay",
            pay_method: "kakaopay",
            mercahnt_uid: `mid_${new Date().getTime()}`,
            amount: product.productPrice,
            name: product.productName,
            buyer_name: principal?.data?.data?.name,
            buyer_email: principal?.data?.data?.email
        }
        IMP.request_pay(paymentData, (response) => {
            const { success, error_msg } = response;
            if(!!success) {
                // 우리 서버에 주문 데이터 insert
                const orderData = {
                    productId: product.productId,
                    email: principal?.data?.data?.email
                }
                orderToServer(orderData).then(response => {
                    alert("포인트 충전이 완료되었습니다.");
                    // queryClient.refetchQueries(["getPrincipal"]);
                    navigate("/account/mypage")
                });
            }else {
                alert(error_msg);
            }
        });
    }

    return (
        <RootContainer>
            <h1>포인트 충전하기</h1>
            <div css={SStoreContainer}>
                    {!getProducts.isLoading && getProducts?.data?.data.map(product => {
                        return <button key={product.productId} onClick={() => handlePaymentSubmit(product)} css={SProductContainer}>{product.productName}</button>
                    })}
            </div>
        </RootContainer>
    );
}

export default StorePage;