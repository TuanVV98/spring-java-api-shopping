import http from '../http-commom/httpAuth';
import authHeader from "./Auth-header.js";
const findByIsNull = () => {
    return http.get("/api/v1/categories");
}

const create = (data) => {
    return http.post("/api/v1/categories",data, { headers: authHeader() })
}

const update = (id, data) => {
    return http.put(`/api/v1/categories/${id}`, data, { headers: authHeader() });
}

export default {
    findByIsNull,
    create,
    update,
}
