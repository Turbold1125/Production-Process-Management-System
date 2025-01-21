import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/auth";

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

const reAuthenticate = async (email, password) => {
  return handleRequest(() => axios.post(`${API_BASE_URL}/reauth`, { email, password }));
};

const login = (userData) => {
    return handleRequest(() => axios.post(`${API_BASE_URL}/login`, userData));
  };

export const authService = {
  reAuthenticate,
  login,
};
