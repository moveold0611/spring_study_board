import instance from "../utils/instance"

export const signup = async (user) => {
    const response = await instance.post("/auth/signup", user);
    console.log(response)
    return response;
}

export const signin = async (signinUser) => {
    const response = await instance.post("/auth/signin", signinUser)
    return response;
}

export const authToken = async () => {
    const response = await instance.get("/account/principal")
    return response;
}