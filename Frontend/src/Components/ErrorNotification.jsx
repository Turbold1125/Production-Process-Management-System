import React from "react";
import { notification } from "antd";
import { CloseCircleOutlined } from "@ant-design/icons";

// Алдааны component
const ErrorNotification = {
  show: (error) => {
    notification.error({
      message: "Алдаа гарлаа!",
      description: error.message || "Алдаа гарлаа. Дахин оролдоно уу.",
      icon: <CloseCircleOutlined style={{ color: "red" }} />,
      placement: "topRight",
      duration: 5,
      style: {
        borderRadius: "8px",
        backgroundColor: "#FFF1F0",
        border: "1px solid #FFA39E",
      },
    });
  },
};

export default ErrorNotification;
