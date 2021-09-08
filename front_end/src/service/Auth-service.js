import http from '../http-commom/httpAuth';


const register = (data) => {
    return http.post("/api/v1/user/register", data);
}

const registerProfile = (data) => {
    return http.post("/api/v1/user/profile", data);
}

const login = (data) => {
    return http.post("/api/v1/user/auth", data);
}

const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem("myUser"));
};
const image = (file) => {
    return http.post("/api/v1/products", file,
        {
            headers: {
                "Content-Type": "multipart/form-data",
            }
        });
}


export default {
    register,
    registerProfile,
    login,
    image,
    getCurrentUser
}