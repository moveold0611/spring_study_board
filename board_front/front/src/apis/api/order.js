import instance from "../utils/instance"

export const orderToServer = async (orderData) => {
    const response = await instance.post("/order", orderData)
    return response;
}   