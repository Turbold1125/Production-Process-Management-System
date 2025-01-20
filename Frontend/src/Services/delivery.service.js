import apiClient from "../Utils/apiClient";

const fetchAllDelivered = async () => {
  const response = await apiClient.get("/delivery/delivered");
  return response.data;
};

const deliverInventory = async (request) => {
  const response = await apiClient.post("/delivery/deliver-inventory", request);
  return response.data;
};

export const deliveryService = {
  fetchAllDelivered,
  deliverInventory,
};
