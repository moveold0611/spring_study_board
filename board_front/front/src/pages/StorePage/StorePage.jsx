import React, { useState } from 'react';
import RootContainer from '../../components/RootContainer/RootContainer';
/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react';
import { useQuery } from 'react-query';
import { getProductsByServer } from '../../apis/api/store';

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
`

function StorePage(props) {
    const [ products, setProducts ] = useState([]);
    const getProducts = useQuery(["getProducts"], async ()=> {
        try {
            return await getProductsByServer();
        } catch (error) {
            
        }
    },{
        refetchOnWindowFocus: false,
        retry: 0,
        onSuccess: () => {
            setProducts(getProducts?.data?.data);
        }
    })



    
    return (
        <RootContainer>
            <h1>포인트 충전하기</h1>
            <div css={SStoreContainer}>

                    {!getProducts.isLoading && products.map(product => {
                        return <button css={SProductContainer}>{product.productName}</button>
                    })}
            </div>
        </RootContainer>
    );
}

export default StorePage;