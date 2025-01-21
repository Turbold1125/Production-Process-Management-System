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

const startProcess = async (payload) => {
  return handleRequest(() => axios.post(`${API_BASE_URL}/processes/start`, payload));
};

const endProcess = async (payload) => {
  return handleRequest(() => axios.post(`${API_BASE_URL}/processes/end`, payload));
};

const requiredMaterial = async (processName, customerName) => {
  return handleRequest(() => axios.get(`${API_BASE_URL}/processes/required-materials`, {
      params: { processName, customerName },
    })
  );
};

const fetchOrderProcesses = async (orderId) => {
  return handleRequest(() => axios.get(`${API_BASE_URL}/order/${orderId}/processes`));
}

export const processService = {
  startProcess,
  endProcess,
  requiredMaterial,
  fetchOrderProcesses
};
