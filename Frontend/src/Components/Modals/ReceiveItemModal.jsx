import React, { useEffect, useState } from 'react';
import { Typography, Modal, Form, Select, Input, Button, Table, message, Row, Col } from 'antd';
import { DeleteOutlined, PlusOutlined } from '@ant-design/icons';
import { constantsService } from '../../Services/constants.service';
import { inventoryService } from '../../Services/Inventory.service';
import { selectedItemsColumns } from '../Inventory/Columns';

const { Title } = Typography;
const { Option } = Select;

const ReceiveItemModal = ({
    isModalVisible,
    handleCancel,
    setRefresh,
    defaultCustomerName = null,
    defaultFiberColor = null,
    defaultFiberType = null,
    defaultMaterial = null
}) => {
    const [fiberColors, setFiberColors] = useState([]);
    const [fiberTypes, setFiberTypes] = useState([]);
    const [customers, setCustomers] = useState([]);
    const [materials, setMaterials] = useState([]);
    const [dataSource, setDataSource] = useState([]);

    const [selectedMaterial, setSelectedMaterial] = useState('');

    const [form] = Form.useForm();

    useEffect(() => {
        if (isModalVisible) {
            fetchModalData();
            console.log(`defaultCustomerName: ${defaultCustomerName}, defaultFiberColor: ${defaultFiberColor}, defaultFiberType: ${defaultFiberType}, defaultMaterial: ${defaultMaterial}`);
        }
    }, [isModalVisible]);

    const fetchModalData = async () => {
        try {
            const [colors, types, customers, materials] = await Promise.all([
                constantsService.fetchAllFiberColors(),
                constantsService.fetchAllFiberTypes(),
                constantsService.fetchAllCustomers(),
                constantsService.fetchAllMaterials(),
            ]);
            setFiberColors(colors);
            setFiberTypes(types);
            setCustomers(customers);
            setMaterials(materials);
        } catch (error) {
            message.error('Error loading data for the modal.');
        }
    };

    const handleAddItem = (values) => {
        const newItem = {
            key: Date.now(),
            ...values,
        };
        setDataSource([...dataSource, newItem]);
        form.setFieldsValue({
            fiberMaterial: values.fiberMaterial,
            customerName: values.customerName,
            fiberColor: values.fiberColor,
            fiberType: values.fiberType,
            roughWeight: null,
            baleWeight: null,
            bobbinWeight: null,
            bobbinNum: null,
        });
        message.success('Жагсаалтанд нэмэгдлээ');
    };

    const handleDeleteRow = (key) => {
        setDataSource(dataSource.filter((item) => item.key !== key));
    };

    const handleReceiveItems = async () => {
        try {
            await Promise.all(
                dataSource.map(async (item) => {
                    const requestBody = {
                        fiberMaterial: item.fiberMaterial,
                        customerName: item.customerName,
                        fiberColor: item.fiberColor,
                        fiberType: item.fiberType,
                        roughWeight: item.roughWeight,
                        baleWeight: item.baleWeight || null,
                        bobbinWeight: item.bobbinWeight || null,
                        bobbinNum: item.bobbinNum || null,
                    };
                    return inventoryService.createInventory(requestBody);
                })
            );
            message.success('Items received successfully!');
            setRefresh(true);
            setDataSource([]);
            form.resetFields();
            handleCancel();
        } catch (error) {
            message.error('Error receiving items.');
        }
    };

    return (
        <Modal
            title={
                <Title level={4} style={{ textAlign: 'center', padding: '12px 0px' }}>
                    Түүхий эд хүлээн авах
                </Title>
            }
            visible={isModalVisible}
            onCancel={handleCancel}
            footer={[
                <Button key="cancel" onClick={handleCancel}>
                    Цуцлах
                </Button>,
                <Button key="submit" type="primary" onClick={handleReceiveItems} disabled={dataSource.length === 0}>
                    Хадгалах
                </Button>,
            ]}
            width={1000}
        >
            <Form
                form={form}
                layout="vertical"
                onFinish={handleAddItem}
                style={{ marginBottom: '16px' }}
                initialValues={{
                    customerName: defaultCustomerName,
                    fiberColor: defaultFiberColor,
                    fiberType: defaultFiberType,
                    fiberMaterial: defaultMaterial,
                }}
            >
                <Row gutter={24}>
                    <Col span={12}>
                        <Form.Item
                            name="fiberMaterial"
                            label="Түүхий эдийн төрөл"
                            rules={[{ required: true, message: 'Түүхий эдийн төрлийг сонгоно уу!' }]}
                        >
                            <Select
                                placeholder="Түүхий эдийн төрлийг сонгоно уу"
                                onChange={(value) => setSelectedMaterial(value)}
                                style={{ borderRadius: '8px' }}
                            >
                                {materials.map(fiberMaterial => (
                                    <Option key={fiberMaterial.id} value={fiberMaterial.name}>{fiberMaterial.name}</Option>
                                ))}
                            </Select>
                        </Form.Item>
                    </Col>
                    <Col span={12}>
                        <Form.Item
                            name="customerName"
                            label="Харилцагч"
                            rules={[{ required: true, message: 'Харилцагч сонгоно уу!' }]}
                        >
                            <Select
                                placeholder="Харилцагч сонгоно уу"
                                style={{ borderRadius: '8px' }}
                                disabled={!!defaultCustomerName}
                            >
                                {Array.isArray(customers) && customers.map(customer => (
                                    <Option key={customer.id} value={customer.name}>{customer.name}</Option>
                                ))}
                            </Select>
                        </Form.Item>
                    </Col>
                </Row>
                <Row gutter={24}>
                    <Col span={12}>
                        <Form.Item
                            name="fiberColor"
                            label="Өнгө"
                            rules={[{ required: true, message: 'Өнгө сонгоно уу!' }]}
                        >
                            <Select
                                placeholder="Өнгө сонгоно уу"
                                style={{ borderRadius: '8px' }}
                            >
                                {Array.isArray(fiberColors) && fiberColors.map(fiberColor => (
                                    <Option key={fiberColor.id} value={fiberColor.name}>{fiberColor.name}</Option>
                                ))}
                            </Select>
                        </Form.Item>
                    </Col>
                    <Col span={12}>
                        <Form.Item
                            name="fiberType"
                            label="Төрөл"
                            rules={[{ required: true, message: 'Төрөл сонгоно уу!' }]}
                        >
                            <Select
                                placeholder="Төрөл сонгоно уу"
                                style={{ borderRadius: '8px' }}
                                disabled={!!defaultFiberType}
                            >
                                {Array.isArray(fiberTypes) && fiberTypes.map(type => (
                                    <Option key={type.id} value={type.name}>{type.name}</Option>
                                ))}
                            </Select>
                        </Form.Item>
                    </Col>
                </Row>
                <Row gutter={24}>
                    <Col span={12}>
                        <Form.Item
                            name="roughWeight"
                            label="Бохир жин (кг)"
                            rules={[{ required: true, message: 'Бохир жинг оруулна уу!' }]}
                        >
                            <Input
                                type="number"
                                style={{ borderRadius: '8px' }}
                            />
                        </Form.Item>
                    </Col>
                    {selectedMaterial === 'Түүхий эд' || selectedMaterial === 'Хольсон түүхий эд' ? (
                        <Col span={12}>
                            <Form.Item
                                name="baleWeight"
                                label="Шуудайны жин (кг)"
                                rules={[{ required: true, message: 'Шуудайны жинг оруулна уу!' }]}
                            >
                                <Input
                                    type="number"
                                    style={{ borderRadius: '8px' }}
                                />
                            </Form.Item>
                        </Col>
                    ) : (
                        <>
                            <Col span={12}>
                                <Form.Item
                                    name="bobbinWeight"
                                    label="Бобины жин (кг)"
                                    rules={[{ required: true, message: 'Бобины жинг оруулна уу!' }]}
                                >
                                    <Input
                                        type="number"
                                        style={{ borderRadius: '8px' }}
                                    />
                                </Form.Item>
                            </Col>
                            <Col span={12}>
                                <Form.Item
                                    name="bobbinNum"
                                    label="Бобины дугаар"
                                    rules={[{ required: true, message: 'Бобины дугаарыг оруулна уу!' }]}
                                >
                                    <Input
                                        type="number"
                                        style={{ borderRadius: '8px' }}
                                    />
                                </Form.Item>
                            </Col>
                        </>
                    )}
                </Row>
                <Button type="dashed" htmlType="submit" icon={<PlusOutlined />} style={{ width: '100%' }}>
                    Жагсаалтанд нэмэх
                </Button>
            </Form>
            <Table
                dataSource={dataSource}
                columns={selectedItemsColumns(handleDeleteRow)}
                pagination={false}
                rowKey="key"
                bordered
            />
        </Modal>
    );
};

export default ReceiveItemModal;
