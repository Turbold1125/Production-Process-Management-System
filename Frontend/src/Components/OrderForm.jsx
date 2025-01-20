import React, { useState, useEffect } from "react";
import { Modal, Form, InputNumber, Select, message } from "antd";
import { constantsService } from "../Services/constants.service";
import { orderService } from "../Services/order.service";

const { Option } = Select;

const SelectField = ({ label, name, options, placeholder, onChange, rules }) => (
  <Form.Item label={label} name={name} rules={rules}>
    <Select
      showSearch
      placeholder={placeholder}
      className="w-full"
      onChange={onChange}
      notFoundContent={`${label} байхгүй байна`}
      filterOption={(input, option) =>
        option.children.toLowerCase().indexOf(input.toLowerCase()) >= 0
      }
    >
      {options.map(({ id, name }) => (
        <Option key={id} value={name}>
          {name}
        </Option>
      ))}
    </Select>
  </Form.Item>
);

const OrderForm = ({ isModalVisible, handleOk, handleCancel }) => {
  const [newOrder, setNewOrder] = useState({});
  const [form] = Form.useForm();

  const [fiberColors, setFiberColors] = useState([]);
  const [fiberTypes, setFiberTypes] = useState([]);
  const [customers, setCustomers] = useState([]);
  const [factoryProcesses, setFactoryProcesses] = useState([]);

  useEffect(() => {
    if (isModalVisible) {
      fetchModalData();
    }
  }, [isModalVisible]);

  const fetchModalData = async () => {
    try {
      const [colors, types, customers, processes] = await Promise.all([
        constantsService.fetchAllFiberColors(),
        constantsService.fetchAllFiberTypes(),
        constantsService.fetchAllCustomers(),
        constantsService.fetchAllFactoryProcesses(),
      ]);
      setFiberColors(colors);
      setFiberTypes(types);
      setCustomers(customers);
      setFactoryProcesses(processes);
    } catch (error) {
      message.error('Error loading data for the modal.');
    }
  };

  const handleCreateOrder = async () => {
    try {
      await form.validateFields();

      const requestBody = {
        customerName: newOrder.customerName,
        fiberColor: newOrder.fiberColor,
        fiberType: newOrder.fiberType,
        factoryProcesses: newOrder.processIds.map((id) => ({ id })),
        weight: newOrder.weight,
      };

      await orderService.createOrder(requestBody);
      message.success("Захиалга амжилттай үүслээ");
      form.resetFields();
      handleOk();
    } catch (error) {
      message.error(error.message);
    }
  };

  return (
    <Modal
      title={
        <div className="text-lg font-bold text-gray-800">
          Захиалга үүсгэх
        </div>
      }
      open={isModalVisible}
      onOk={handleCreateOrder}
      onCancel={handleCancel}
      className="rounded-lg shadow-lg"
      okButtonProps={{
        className: "bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-md",
      }}
      cancelButtonProps={{
        className: "bg-gray-200 hover:bg-gray-300 text-gray-800 px-4 py-2 rounded-md",
      }}
    >
      <Form
        layout="vertical"
        form={form}
        className="space-y-4"
      >
        <SelectField
          label="Харилцагч"
          name="customer"
          options={customers || []}
          placeholder="Харилцагч сонгох"
          onChange={(value) =>
            setNewOrder({ ...newOrder, customerName: value })
          }
          rules={[{ required: true, message: "Харилцагч сонгоно уу" }]}
        />
        <SelectField
          label="Өнгө"
          name="color"
          options={fiberColors || []}
          placeholder="Өнгө сонгох"
          onChange={(value) =>
            setNewOrder({ ...newOrder, fiberColor: value })
          }
          rules={[{ required: true, message: "Өнгө сонгоно уу" }]}
        />
        <SelectField
          label="Төрөл"
          name="type"
          options={fiberTypes || []}
          placeholder="Төрөл сонгох"
          onChange={(value) =>
            setNewOrder({ ...newOrder, fiberType: value })
          }
          rules={[{ required: true, message: "Төрөл сонгоно уу" }]}
        />
        <Form.Item
          label="Процесс"
          name="processes"
          rules={[{ required: true, message: "Процесс сонгоно уу" }]}
        >
          <Select
            mode="multiple"
            placeholder="Процесс сонгох"
            className="w-full"
            onChange={(value) =>
              setNewOrder({
                ...newOrder,
                processIds: value.map(Number),
              })
            }
          >
            {(factoryProcesses || []).map(({ id, name }) => (
              <Option key={id} value={id}>
                {name}
              </Option>
            ))}
          </Select>
        </Form.Item>
        <Form.Item
          label="Жин (кг)"
          name="weight"
          rules={[{ required: true, message: "Жин оруулна уу" }]}
        >
          <InputNumber
            min={1}
            className="w-full"
            onChange={(value) =>
              setNewOrder({ ...newOrder, weight: value })
            }
          />
        </Form.Item>
      </Form>
    </Modal>
  );
};

export default OrderForm;
