import instance from "../utils/instance";

export const uploadProfileImg = async (imgUrl) => {
    const response = await instance.put("/account/profile/img", imgUrl)
    return response;
}

export const updatePassword = async (newPassword) => {
    const response = await instance.put("/account/password", newPassword)
    return response;
}