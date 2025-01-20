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

const fetchAllinventory = async () => {
  return handleRequest(() => axios.get(`${API_BASE_URL}/inventory/all`));
};

const fetchInventoryLogs = async () => {
  return handleRequest(() => axios.get(`${API_BASE_URL}/inventory/logs`));
};

const createInventory = async (data) => {
  return handleRequest(() =>
    axios.post(`${API_BASE_URL}/inventory/create`, data)
  );
};

const searchInventory = async (customerName, fiberMaterial) => {
  return handleRequest(() =>
    axios.get(`${API_BASE_URL}/inventory/search`, {
      params: { customerName, fiberMaterial },
    })
  );
};

const filterInventory = async (customer, material, color, fiberType) => {
  return handleRequest(() =>
    axios.get(`${API_BASE_URL}/inventory/filter`, {
      params: { customer, material, color, fiberType },
    })
  );
};

export const inventoryService = {
  fetchAllinventory,
  fetchInventoryLogs,
  createInventory,
  searchInventory,
  filterInventory,
};
