import instance from "../utils/instance"

export const signup = async (user) => {
    console.log(1)
    const response = await instance.post("/auth/signup", user);
    console.log(response)
    return response;
}

