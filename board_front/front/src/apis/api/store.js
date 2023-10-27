import instance from "../utils/instance"

export const getProductsByServer = async () => {
    const response = await instance.get("/products")
    return response;
}   