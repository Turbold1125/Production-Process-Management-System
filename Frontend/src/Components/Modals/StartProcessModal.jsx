import React, { useContext, useState } from "react";
import { Modal, Table, Typography, message } from "antd";
import { inventoryColumns } from "../OrderDetails/Columns";
import { UserContext } from "../../Context/userContext";
import { processService } from "../../Services/process.service";

const { Title } = Typography;

const StartProcessModal = ({
    isModalVisible,
    onCancel,
    selectedFibers,
    setSelectedFibers,
    selectedProcess,
    orderDetails,
    onSuccess
}) => {


    const { currentUser } = useContext(UserContext);
    const totalInputWeight = selectedFibers.reduce(
        (sum, fiber) => sum + (parseFloat(fiber.selectedWeight) || 0),
        0
    );

    const handleStartProcess = async () => {
        const messageKey = "startProcess";
        if (selectedFibers.length === 0) {
            message.error("All necessary fields must be provided.");
            return;
        }
        const payload = {
            processId: selectedProcess.id,
            orderId: orderDetails.id,
            processName: selectedProcess.processName,
            customerName: orderDetails.customerName,
            userId: currentUser.id,
            fibers: selectedFibers.map((fiber) => ({
                inventoryId: fiber.id,
                inputMaterialWeight: fiber.selectedWeight,
                inputMaterial: fiber.fiberMaterial,
            })),
        };
        try {
            message.loading({ content: "Starting process...", key: messageKey, duration: 1 });
            await processService.startProcess(payload);
            message.success({
                content: `Process "${selectedProcess.processName}" started successfully!`,
                key: messageKey,
                duration: 2,
            });
            console.log()
            onSuccess();
        } catch (error) {
            message.error({
                content: error.response?.data?.message || "Failed to start process.",
                key: messageKey,
                duration: 2,
            });
        }
    }

    return (
        <Modal
            title={
                <Title level={4} style={{ textAlign: 'center', padding: '12px 0px' }}>
                    Процесс эхлүүлэх
                </Title>
            }
            visible={isModalVisible}
            onCancel={onCancel}
            onOk={handleStartProcess}
            width={800}
            okText="Эхлүүлэх"
            cancelText="Цуцлах"
        >
            <Table
                columns={inventoryColumns(selectedFibers, setSelectedFibers)}
                dataSource={selectedFibers}
                pagination={false}
                bordered
                size="small"
                rowKey="id"
            />
            <div style={{ marginTop: "10px" }}>
                <strong>Нийт оролтын жин:</strong> {totalInputWeight} кг
            </div>
        </Modal>
    )
}

export default StartProcessModal;