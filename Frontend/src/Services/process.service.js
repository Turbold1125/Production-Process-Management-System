import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const startProcess = async (payload) => {
    const response = await axios.post(`${API_BASE_URL}/processes/start`, payload);
    return response.data;
}

export const processService = {
    startProcess
}