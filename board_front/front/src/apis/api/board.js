import instance from "../utils/instance";

export const addBoard = async (content) => {
    const response = await instance.post("/board/content", content);
    return response;
}

export const updateBoard = async (content) => {
    const response = await instance.put(`/board/content/update`, content);
    return response;
}

export const removeBoard = async (removeData) => {
    const response = await instance.delete(`/board/${removeData}`);
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

export const getBoardDetails = async (boardId) => {
    const response = await instance.get(`/board/details/${boardId}`);
    return response;
}

export const getLikeStateReq = async (boardId) => {
    const response = await instance.get(`/board/like/${boardId}`);
    return response;
}

export const insertBoardLike = async (boardId) => {
    const response = await instance.post(`/board/like/set/${boardId}`)
    return response;
}

export const deleteBoardLike = async (boardId) => {
    const response = await instance.delete(`/board/like/del/${boardId}`);
    return response;
}