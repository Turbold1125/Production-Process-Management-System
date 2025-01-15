import React, { useContext, useState } from "react";
import { Modal, Table, Input, Select, message } from "antd";
import { inventoryColumns } from "./Columns";
import { UserContext } from "../../Context/userContext";
import { processService } from "../../Services/process.service";

const StartProcessModa = ({
    selectedFibers, visible, setSelectedFibers, onCancel, selectedProcess, orderDetails
}) => {


    const { currentUser } = useContext(UserContext);
    const totalInputWeight = selectedFibers.reduce(
        (sum, fiber) => sum + (parseFloat(fiber.selectedWeight) || 0),
        0
    );

    const handleStartProcess = async () => {
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
            await processService.startProcess(payload);
            message.success("Process started successfully!", selectedProcess.processName);
        } catch (error) {
            message.error(error.response?.data?.message);
        }
    }

    return (
        <Modal
            title="Start Process"
            visible={visible}
            onCancel={onCancel}
            onOk={handleStartProcess}
            width={800}
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

export default StartProcessModa;