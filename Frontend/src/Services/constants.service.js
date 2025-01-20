import apiClient from "../Utils/apiClient";

const fetchAllFiberColors = async () => {
  const response = await apiClient.get("/fiberColor");
  return response.data;
};

const fetchAllMaterials = async () => {
  const response = await apiClient.get("/material");
  return response.data;
};

const fetchAllFiberTypes = async () => {
  const response = await apiClient.get("/fiberType");
  return response.data;
};

const fetchAllCustomers = async () => {
  const response = await apiClient.get("/customer");
  return response.data;
};

const fetchAllFactoryProcesses = async () => {
  const response = await apiClient.get("/factoryProcess");
  return response.data;
};

export const constantsService = {
  fetchAllFiberColors,
  fetchAllMaterials,
  fetchAllFiberTypes,
  fetchAllCustomers,
  fetchAllFactoryProcesses,
};
