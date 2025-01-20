import React, { useState, useEffect } from "react";
import { Modal, Select, message, Table } from "antd";

const { Option } = Select;

const DeliverItemsModal = ({ visible, onCancel, inventories, onDeliver }) => {
  const [selectedCustomer, setSelectedCustomer] = useState(null);
  const [filteredInventories, setFilteredInventories] = useState([]);
  const [selectedInventories, setSelectedInventories] = useState([]);

  const uniqueCustomers = [...new Set(
    inventories.filter(item => item.customerName).map(item => item.customerName)
  )];

  useEffect(() => {
    if (selectedCustomer) {
      setFilteredInventories(
        inventories.filter((inventory) => inventory.customerName === selectedCustomer)
      );
    } else {
      setFilteredInventories(inventories);
    }
  }, [selectedCustomer, inventories]);

  const handleDeliver = async () => {
    if (!selectedCustomer) {
      message.error("Хэрэглэгч сонгоно уу.");
      return;
    }
    
    if (selectedInventories.length === 0) {
      message.warning("Түүхий эд сонгоно уу.");
      return;
    }
  
    try {
      await onDeliver({
        customerName: selectedCustomer,
        inventoryIds: selectedInventories,
        deliverAll: false,
        deliveryType: "INVENTORY",
      });
  
      setSelectedInventories([]);
    } catch (error) {
      console.error("Error delivering items:", error);
      message.error("Failed to deliver items. Please try again.");
    }
  };

  return (
    <Modal
      visible={visible}
      onCancel={onCancel}
      onOk={handleDeliver}
      title="Хүлээлгэн өгөх"
      width={800}
    >
      <div style={{ marginBottom: "16px" }}>
        <Select
          placeholder="Хэрэглэгч"
          style={{ width: "100%" }}
          onChange={(value) => setSelectedCustomer(value)}
          allowClear
        >
          {uniqueCustomers.length > 0 ? (
            uniqueCustomers.map((customer) => (
              <Option key={customer} value={customer}>
                {customer}
              </Option>
            ))
          ) : (
            <Option disabled>Хэрэглэгч алга</Option>
          )}
        </Select>
      </div>
      <Table
        rowSelection={{
          type: "checkbox",
          onChange: (selectedRowKeys) => setSelectedInventories(selectedRowKeys),
        }}
        columns={[
          { title: "Дугаар", dataIndex: "id", key: "id" },
          { title: "Харилцагч", dataIndex: "customerName", key: "customerName" },
          { title: "Материал", dataIndex: "fiberMaterial", key: "fiberMaterial" },
          { title: "Шуудай", dataIndex: "baleNum", key: "baleNum"},
          { title: "Жин", dataIndex: "conWeight", key: "conWeight" },
          { title: "Огноо", dataIndex: "dateTime", key: "dateTime" },
        ]}
        dataSource={filteredInventories}
        rowKey="id"
        pagination={{ pageSize: 5 }}
      />
    </Modal>
  );
};

export default DeliverItemsModal;
