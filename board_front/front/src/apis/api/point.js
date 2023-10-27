import instance from "../utils/instance"

export const pointUse = async (pointUseData) => {
    const response = await instance.post("/usepoint", pointUseData)
    return response;
}   