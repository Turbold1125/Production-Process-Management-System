import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api";

const apiClient = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

// Intercept responses to handle errors globally
apiClient.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && error.response.data && error.response.data.message) {
      return Promise.reject(new Error(error.response.data.message));
    }
    return Promise.reject(new Error("An error occurred. Please try again."));
  }
);

export default apiClient;


// import axios from "axios";
// import { message } from "antd"; // Ant Design-ийн алдааны мэдэгдэл ашиглах

// const API_BASE_URL = "http://localhost:8080/api";

// const apiClient = axios.create({
//   baseURL: API_BASE_URL,
//   headers: {
//     "Content-Type": "application/json",
//   },
// });

// // Хариултыг интерцептор ашиглан боловсруулах
// apiClient.interceptors.response.use(
//   (response) => response, // Амжилттай хүсэлтийн үед
//   (error) => {
//     // Алдааны мэдэгдэл харуулах
//     if (error.response && error.response.data && error.response.data.message) {
//       message.error(error.response.data.message); // API-с ирсэн алдааны мессежийг харуулах
//     } else {
//       message.error("Алдаа гарлаа. Дахин оролдоно уу."); // Ерөнхий алдааны мессеж
//     }

//     // Алдааг дахин буцааж, хэрэглэгч өөрийн хүсэлтэд ажиллах боломжтой болгох
//     return Promise.reject(error);
//   }
// );

// export default apiClient;
