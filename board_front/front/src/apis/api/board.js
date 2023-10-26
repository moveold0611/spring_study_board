import instance from "../utils/instance";

export const addBoard = async (content) => {
    const response = await instance.post("/board/content", content);
    return response;
}

export const getCategory = async () => {
    const response = await instance.get("/board/category");
    return response;
}

export const getBoards = async (category, page, option) => {
    const response = await instance.get(`boards/${category}/${page}`, option);
    return response;
}

export const getBoardCount = async (categoryName, option) => {
    const response = await instance.get(`/board/${categoryName}/all`, option);
    return response.data;
}