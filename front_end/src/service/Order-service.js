import http from '../http-commom/httpAuth';
import authHeader from "./Auth-header.js";

const create_order = (data) => {
    return http.post("/api/v1/orders", data, { headers: authHeader() })
}

const update_order = (id,data) => {
    return http.put(`/api/v1/orders/${id}`, data, { headers: authHeader() })
}

const getAll = () => {
    return http.get("/api/v1/orders", { headers: authHeader() })
}

const findByUserId = (id) => {
    return http.get(`/api/v1/orders/findBy?userId=${id}`, { headers: authHeader() })
}
const findByOrderId = (id) => {
    return http.get(`/api/v1/order/details/findBy?orderId=${id}`, { headers: authHeader() })
}

const create_order_details = (data) => {
    return http.post("/api/v1/order/details", data, { headers: authHeader() })
}



export default {
    create_order,
    update_order,
    create_order_details,
    getAll,
    findByOrderId,
    findByUserId
}