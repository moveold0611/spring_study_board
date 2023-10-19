import instance from "../utils/instance";

export const sendMail = async () => {
    const response = await instance.post("/account/mail/auth")
    return response;
}