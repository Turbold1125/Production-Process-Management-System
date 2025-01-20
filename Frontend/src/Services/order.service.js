import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api";

const fetchAllOrder = async () => {
  const response = await axios.get(`${API_BASE_URL}/order/all`);
  return response.data;
};

const createOrder = async (data) => {
    const response = await axios.post(`${API_BASE_URL}/order/create`, data);
    return response.data;
}

export const orderService = {
    fetchAllOrder,
    createOrder,
}