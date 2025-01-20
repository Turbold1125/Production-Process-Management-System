import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api";

const handleRequest = async (apiCall) => {
  try {
    const response = await apiCall();
    return response.data;
  } catch (error) {
    if (error.response && error.response.data && error.response.data.message) {
      throw new Error(error.response.data.message);
    } else {
      throw new Error("An error occurred. Please try again.");
    }
  }
};

const fetchAllOrders = async () => {
  return handleRequest(() => axios.get(`${API_BASE_URL}/order/all`));
};

const fetchRecentOrders = async () => {
  return handleRequest(() => axios.get(`${API_BASE_URL}/order/recent`));
};

const createOrder = async (data) => {
  return handleRequest(() => axios.post(`${API_BASE_URL}/order/create`, data));
};

const deleteOrder = async (data) => {
  return handleRequest(() => axios.delete(`${API_BASE_URL}/order/delete/${data}`));
}

export const orderService = {
  fetchAllOrders,
  fetchRecentOrders,
  createOrder,
  deleteOrder
};
