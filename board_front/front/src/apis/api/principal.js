import instance from "../utils/instance";

export const connentPrincipal = async () => {
    const response = await instance.get("/account/principal")
    return response;
}