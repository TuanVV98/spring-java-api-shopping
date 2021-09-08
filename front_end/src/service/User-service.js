import http from '../http-commom/httpAuth';
import authHeader from "./Auth-header.js";

const findByUserId = (id) => {
    return http.get(`/api/v1/user/profile/findBy?id=${id}`, { headers: authHeader() })
}

const update = (id,data) => {
    return http.put(`/api/v1/users/${id}`,data, { headers: authHeader() })
}


export default {
    findByUserId,
    update
}