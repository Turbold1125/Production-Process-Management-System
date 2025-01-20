import React, { useState, useEffect } from "react";
import { Modal, Table, Spin, message, Tag } from "antd";
import { getOrderProcesses } from "../../routes";
import { processDetailColumns } from "../Columns/ProcessColumns";

const ProcessDetailsModal = ({ visible, orderId, onClose }) => {
  const [loading, setLoading] = useState(false);
  const [processes, setProcesses] = useState([]);

  useEffect(() => {
    if (visible && orderId) {
      fetchProcesses(orderId);
    }
  }, [visible, orderId]);

  const fetchProcesses = async (orderId) => {
    setLoading(true);
    try {
      const data = await getOrderProcesses(orderId);
      setProcesses(data);
    } catch (error) {
      message.error("Failed to fetch processes.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <Modal
      visible={visible}
      title="Процессийн дэлгэрэнгүй"
      onCancel={onClose}
      footer={null}
      width={800}
    >
      {loading ? (
        <Spin size="large" />
      ) : (
        <Table
          columns={processDetailColumns}
          dataSource={processes}
          rowKey="id"
          pagination={false}
        />
      )}
    </Modal>
  );
};

export default ProcessDetailsModal;
