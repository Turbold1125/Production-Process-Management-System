import React from "react";
import { EyeOutlined } from "@ant-design/icons";
import { Button, Tag, Tooltip } from "antd";
import { formatDate } from "../../Utils/DateFormat";
import { StatusColorMap } from "../../Constants/Colors";

export const orderColumns = (viewOrderProcesses, navigate) => [
    {
      title: "Захиалгын дугаар",
      dataIndex: "id",
      key: "id",
      width: 120,
      align: "center",
    },
    {
      title: "Харилцагч",
      dataIndex: ["customerName"],
      key: "name",
      align: "center",
    },
    {
      title: "Огноо",
      dataIndex: "orderDate",
      key: "orderDate",
      align: "center",
      render: (dateTime) => (dateTime ? formatDate(dateTime) : "-"),
      sorter: (a, b) => new Date(a.orderDate) - new Date(b.orderDate),
      defaultSortOrder: "descend",
    },
    {
      title: "Өөрчилсөн огноо",
      dataIndex: "updatedAt",
      key: "updatedAt",
      align: "center",
      render: (updatedAt) => (updatedAt ? formatDate(updatedAt) : "-"),
      sorter: (a, b) => new Date(a.updatedAt) - new Date(b.updatedAt),
      defaultSortOrder: "descend",
    },
    {
      title: "Өнгө",
      dataIndex: ["fiberColor"],
      key: "color",
      align: "center",
      render: (color) => <Tag color="geekblue">{color}</Tag>,
    },
    {
      title: "Төлөв",
      dataIndex: "status",
      key: "status",
      align: "center",
      render: (status) => <Tag color={StatusColorMap[status]}>{status}</Tag>,
    },
    {
      title: "Жин",
      dataIndex: "weight",
      key: "weight",
      align: "center",
      render: (text) => `${text} кг`,
    },
    {
      title: "Процесс харах",
      key: "processActions",
      align: "center",
      render: (_, record) => (
        <Tooltip title="Процессын дэлгэрэнгүй">
          <Button
            type="primary"
            shape="circle"
            icon={<EyeOutlined />}
            onClick={() => viewOrderProcesses(record.id)}
            className="bg-green-500 hover:bg-green-600 text-white border-none shadow-md transition duration-200 ease-in-out"
          />
        </Tooltip>
      ),
      width: 140,
    },
    {
      title: "Дэлгэрэнгүй",
      key: "orderActions",
      align: "center",
      render: (_, record) => (
        <Button
          type="primary"
          onClick={() => navigate(`/order/${record.id}`)}
          className="bg-blue-500 hover:bg-blue-600 text-white border-none shadow-md transition duration-200 ease-in-out"
        >
          Дэлгэрэнгүй
        </Button>
      ),
      width: 150,
    },
  ];