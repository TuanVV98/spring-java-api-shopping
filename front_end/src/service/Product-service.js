import http from '../http-commom/httpAuth';
import authHeader from "./Auth-header.js";
const create = (data) => {
    return http.post("/api/v1/products", data);
}
const update = (id,data) => {
    return http.put(`/api/v1/products/${id}`, data, { headers: authHeader() });
}

const products_by_category_url = (name) => {
    return http.get`/api/v1/products/byCategory?name=${name}`;
}

export default {
    create,
    products_by_category_url,
    update
}