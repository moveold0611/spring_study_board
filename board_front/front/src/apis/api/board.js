import instance from "../utils/instance";

export const addBoard = async (content) => {
    const response = await instance.post("/board", content);
    return response;
}

export const getCategory = async () => {
    const response = await instance.get("/board/category");
    return response;
}