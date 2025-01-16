import React, { useContext, useEffect, useState } from "react";
import { Modal, Select, Input, message, Typography } from "antd";
import { UserContext } from "../../Context/userContext";
import { processService } from "../../Services/process.service";
import { constantsService } from "../../Services/constants.service";

const { Title } = Typography;
const { Option } = Select;

const EndProcessModal = ({
    isModalVisible, onCancel, orderDetails, selectedProcess, onSuccess
}) => {
    const { currentUser } = useContext(UserContext);

    const [outputColor, setOutputColor] = useState("");
    const [fiberColors, setFiberColors] = useState([]);
    const [outputWeight, setOutputWeight] = useState(0);

    const [wasteData, setWasteData] = useState({});
    const [wastes, setWastes] = useState([]);

    useEffect(() => {
        if (isModalVisible) {
            fetchFiberColors();
            initializeWasteData();
        }
    }, [isModalVisible]);

    const fetchFiberColors = async () => {
        try {
            const response = await constantsService.fetchAllFiberColors();
            setFiberColors(response)
        } catch (error) {
            message.error("Өнгөний мэдээллийг авахад алдаа гарлаа.");
        }
    }

    const initializeWasteData = () => {
        const factoryProcess = orderDetails?.factoryProcesses?.find(
            (fp) => fp.name === selectedProcess.processName
        );

        if (factoryProcess?.waste) {
            const wasteList = factoryProcess.waste.split(", ").map((waste) => waste.trim());
            setWastes(wasteList);

            const initialWasteData = wasteList.reduce((acc, waste) => {
                acc[waste] = 0; // Initialize all waste weights to 0
                return acc;
            }, {});

            setWasteData(initialWasteData);
        }
    };

    const handleEndProcess = async () => {
        const messageKey = "endProcess";
        const wastesArray = Object.entries(wasteData).map(([material, weight]) => ({
            material,
            weight: parseFloat(weight) || 0,
        }));

        const payload = {
            processId: selectedProcess.id,
            orderId: orderDetails.id,
            userId: currentUser.id,
            fiberType: orderDetails.fiberType,
            outputWeight,
            outputColor,
            wastes: wastesArray,
        };
        console.log(payload);

        try {
            message.loading({ content: "Ending process...", key: messageKey, duration: 1 });

            await processService.endProcess(payload);
            message.success({
                content: `Process "${selectedProcess.processName}" ended successfully!`,
                key: messageKey,
                duration: 2,
            }); onSuccess();
        } catch (error) {
            message.error(error.response?.data?.message);
        }
    }


    return (
        <Modal
            title={
                <Title level={4} style={{ textAlign: 'center', padding: '12px 0px' }}>
                    Процесс дуусгах
                </Title>
            }
            visible={isModalVisible}
            onCancel={onCancel}
            onOk={handleEndProcess}
            okText="Дуусгах"
            cancelText="Цуцлах"
        >
            <div style={{ marginBottom: "16px" }}>
                <label>Гаралтын материал:</label>
                <div
                    style={{
                        padding: "8px 12px",
                        background: "#f5f5f5",
                        border: "1px solid #d9d9d9",
                        borderRadius: "4px",
                        marginTop: "8px",
                    }}
                >
                    {selectedProcess?.outputMaterial || 'unknown'}
                </div>
            </div>
            <div style={{ marginBottom: "16px" }}>
                <div style={{ marginBottom: "8px" }}>
                    <strong>Захиалга дахь өнгө:</strong>
                    {orderDetails.fiberColor || "Өнгө байхгүй"}
                </div>
                <label>Гаралтын өнгө:</label>
                <Select
                    style={{ width: "100%" }}
                    placeholder="Гаралтын өнгийг сонгоно уу"
                    value={outputColor}
                    onChange={setOutputColor}
                    allowClear
                    required
                >
                    {fiberColors.map((color) => (
                        <Option key={color.id} value={color.name}>
                            {color.name}
                        </Option>
                    ))}
                </Select>
            </div>
            <div>
                <label>Гаралтын жин (кг):</label>
                <Input
                    type="number"
                    min={0}
                    value={outputWeight}
                    onChange={(e) => setOutputWeight(e.target.value)}
                    onBlur={() => setOutputWeight(parseFloat(outputWeight) || 0)}
                    placeholder="Гаралтын жин"
                />
            </div>
            <div style={{ marginBottom: "16px" }}>
                <div style={{ marginBottom: "16px" }}>
                    <strong>Хаягдлын мэдээлэл:</strong>
                    {wastes.length > 0 ? (
                        wastes.map((waste) => (
                            <div key={waste} style={{ marginBottom: "8px" }}>
                                <label>{waste}:</label>
                                <Input
                                    type="number"
                                    min={0}
                                    value={wasteData[waste] || ""}
                                    onChange={(e) =>
                                        setWasteData({
                                            ...wasteData,
                                            [waste]: parseFloat(e.target.value) || 0,
                                        })
                                    }
                                    placeholder={`Хаягдлын жин (${waste})`}
                                />
                            </div>
                        ))
                    ) : (
                        <p>Энэ процессд хаягдал байхгүй.</p>
                    )}
                </div>
            </div>
        </Modal>
    )
}

export default EndProcessModal;