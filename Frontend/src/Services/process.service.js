import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api";

const startProcess = async (payload) => {
  const response = await axios.post(`${API_BASE_URL}/processes/start`, payload);
  return response.data;
};

const endProcess = async (payload) => {
  const response = await axios.post(`${API_BASE_URL}/processes/end`, payload);
  return response.data;
};

const requiredMaterial = async (processName, customerName) => {
  const response = await axios.get(`${API_BASE_URL}/processes/required-materials`, {
      params: { processName, customerName },
    });
    return response.data;
};

export const processService = {
  startProcess,
  endProcess,
  requiredMaterial
};
