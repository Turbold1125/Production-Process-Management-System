import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api";

const downloadOrderReport = async (orderId) => {
    try {
        const response = await axios.get(`${API_BASE_URL}/reports/order/${orderId}`, {
          responseType: "blob",
        });
    
        const url = window.URL.createObjectURL(new Blob([response.data], { type: "application/pdf" }));
        const link = document.createElement("a");
        link.href = url;
        link.setAttribute("download", `Order_Report_${orderId}.pdf`);
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
    
        return { success: true };
      } catch (error) {
        console.error("Error downloading the report:", error);
        throw new Error("PDF татахад алдаа гарлаа.");
      }
};

export const reportService = {
  downloadOrderReport,
};
