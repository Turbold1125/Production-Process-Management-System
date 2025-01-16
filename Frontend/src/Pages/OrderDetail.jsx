import React, { useState, useEffect, useContext } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { Card, Typography, Button, Row, Col, Table, Spin, Alert, message, Tabs, Tag, Form } from "antd";
import { CheckCircleOutlined, FilePdfOutlined, PlayCircleOutlined, PlusOutlined } from "@ant-design/icons";
import axios from "axios";
import useOrderDetails from "../hooks/useOrderDetailss";
import { inventoryColumns, processColumns, processIOColumns, processLogColumns } from "../Components/OrderDetails/Columns";
import { EndProcessModal, StartProcessModal } from "../Components/OrderDetails/Modals";
import StepsComponent from "../Components/OrderDetails/Steps";
import { useColors } from "../hooks/useColors";
import { UserContext } from "../Context/userContext";
import useProcessAction from "../hooks/useProcessAction";
import { useInventory } from "../hooks/useInventory";
import ReceiveItemModal from "../Components/Inventory/ReceiveItemModal";
import Header from "../Components/OrderDetails/Header";
import OrderInfo from "../Components/OrderDetails/OrderInfo";
import StartProcessModa from "../Components/OrderDetails/StartProcessModa";
import EndProcessModa from "../Components/OrderDetails/EndProcessModa";
import InputOutputCard from "../Components/OrderDetails/InputOutputCard";
import { processService } from "../Services/process.service";

const { Title } = Typography;
const { TabPane } = Tabs;

const getStatusTag = (status) => {
  switch (status) {
    case "COMPLETED":
      return <Tag color="green">{status}</Tag>;
    case "IN_PROGRESS":
      return <Tag color="blue">{status}</Tag>;
    case "PENDING":
      return <Tag color="orange">{status}</Tag>;
    default:
      return <Tag>{status}</Tag>;
  }
};

const OrderDetailsLayout = () => {
  const { id: orderId } = useParams();

  const navigate = useNavigate();

  const { orderDetails, processes: initialProcesses, loading, error, processLogs, fetchProcessLogs, fetchProcesses, fetchOrderById, processInputs, processOutputs, fetchProcessInputs, fetchProcessOutputs } = useOrderDetails(orderId);
  const { createInventory } = useInventory();

  const [processes, setProcesses] = useState([]);
  const [selectedProcessIndex, setSelectedProcessIndex] = useState(0);
  const [inventoryLoading, setInventoryLoading] = useState(false);
  const [selectedFibers, setSelectedFibers] = useState([]);
  const [startProcessModalVisible, setStartProcessModalVisible] = useState(false);
  const [filteredInventory, setFilteredInventory] = useState([]);
  const [activeTab, setActiveTab] = useState("1");

  const [endProcessModalVisible, setEndProcessModalVisible] = useState(false);
  
  const [inventory, setNoInventory] = useState(false);

  const [isModalVisible, setIsModalVisible] = useState(false);

  const [form] = Form.useForm();

  const handleReceiveItem = async (values) => {
    const requestBody = {
      fiberMaterial: values.fiberMaterial,
      customerName: values.customerName,
      fiberColor: values.fiberColor,
      fiberType: values.fiberType,
      roughWeight: values.roughWeight,
      baleWeight: values.baleWeight,
      bobbinWeight: values.bobbinWeight,
      baleNum: values.baleNum,
      bobbinNum: values.bobbinNum,
    };

    try {
      await createInventory(requestBody);
      message.success('Амжилттай хүлээн авлаа');
      setIsModalVisible(false);
      form.resetFields();
    } catch (error) {
      message.error('Хүлээн авахад алдаа гарлаа');
    }
  };

  const handleOpenModal = () => {
    setIsModalVisible(true);
  };

  const handleCancel = () => {
    setIsModalVisible(false);
    form.resetFields();
  };

  useEffect(() => {
    setProcesses(initialProcesses);
  }, [initialProcesses]);

  // useEffect(() => {
  //   const fetchRequiredMaterials = async () => {
  //     const selectedProcess = processes[selectedProcessIndex];
  //     if (!selectedProcess) return;

  //     try {
  //       const response = await processService.requiredMaterial(
  //         selectedProcess.processName,
  //         orderDetails?.customerName
  //       );
  //       setFilteredInventory(response);
  //     } catch {
  //       message.error("Шаардлагатай материалыг татаж чадсангүй.");
  //     }
  //   };

  //   fetchRequiredMaterials();
  // }, [selectedProcessIndex, processes, orderDetails]);

  useEffect(() => {
    if (processes.length > 0 && processes[selectedProcessIndex]) {
      fetchMaterialsForProcess(processes[selectedProcessIndex].processName);

      const availableInventory = filteredInventory.length > 0;
      setNoInventory(!availableInventory);
    }
  }, [selectedProcessIndex, processes]);

  const fetchMaterialsForProcess = async (processName) => {
    try {
      const response = await processService.requiredMaterial(processName, orderDetails?.customerName);
      setFilteredInventory(response);
    } catch (error) {
      message.error("Шаардлагатай материалыг татаж чадсангүй.");
    }
  };

  const handleProcessClick = (index) => {
    setSelectedProcessIndex(index);
    console.log("OrderDetails:::", orderDetails)
  };

  const handleStartProcessSuccess = () => {
    setStartProcessModalVisible(false);
    fetchProcesses();
    fetchProcessLogs();
    fetchProcessInputs();
    fetchProcessOutputs();
    fetchOrderById(orderId);
  }

  const handleEndProcessSuccess = () => {
    setEndProcessModalVisible(false);
    fetchProcesses();
    fetchProcessLogs();
    fetchProcessInputs();
    fetchProcessOutputs();
    fetchOrderById(orderId);
  }


  /* ---------------------------------------- */





  const handleTabChange = (key) => {
    setActiveTab(key);

    if (key === "2") {
      const firstIncompleteIndex = processes.findIndex(
        (process) => process.status !== "COMPLETED"
      );

      setSelectedProcessIndex(firstIncompleteIndex !== -1 ? firstIncompleteIndex : 0);
    }
  };

  const handleEndProcess = async () => {
    const selectedProcess = processes[selectedProcessIndex];
    if (!selectedProcess) {
      message.error("Процесс сонгогдоогүй байна.");
      return;
    }
    setEndProcessModalVisible(true);
  };

  const handleDownloadReport = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/reports/order/${orderId}`, {
        responseType: 'blob',
      });

      const url = window.URL.createObjectURL(new Blob([response.data], { type: 'application/pdf' }));
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', `Order_Report_${orderId}.pdf`);
      document.body.appendChild(link);
      link.click();
      link.parentNode.removeChild(link);

      message.success("PDF татах амжилттай боллоо.");
    } catch (error) {
      console.error("Error downloading the report:", error);
      message.error("PDF татахад алдаа гарлаа.");
    }
  };

  if (loading) { return (<div style={{ textAlign: "center", marginTop: "50px" }}><Spin size="large" /></div>); }

  if (error) { return (<Alert message="Алдаа" description={error} type="error" showIcon style={{ margin: "20px" }} />); }

  if (!orderDetails) { return (<Alert message="Захиалгын дэлгэрэнгүй мэдээлэл олдсонгүй" type="warning" showIcon style={{ margin: "20px" }} />); }


  const canStartProcess = (selectedIndex) => {
    if (selectedIndex === 0) return true; // Эхний процесс байнга эхлэх боломжтой
    const previousProcess = processes[selectedIndex - 1];
    return previousProcess?.status === "COMPLETED";
  };


  return (
    <div style={{ padding: "10px", maxWidth: "1800px", margin: "0 auto" }}>

      <Header orderId={orderId} navigate={navigate} />

      <Tabs
        activeKey={activeTab}
        onChange={(key) => handleTabChange(key)}>
        <TabPane tab="Захиалгын дэлгэрэнгүй" key="1">
          <Row gutter={[16, 16]}>
            {/* Захиалгын дэлгэрэнгүй */}
            <Col xs={24} md={16}>

              <OrderInfo orderDetails={orderDetails} getStatusTag={getStatusTag} />

              <InputOutputCard
                title="Гаралтын процесс лог"
                columns={processIOColumns}
                dataSource={processOutputs}
              />

              <InputOutputCard
                title="Оролтын процесс лог"
                columns={processIOColumns}
                dataSource={processInputs}
              />

            </Col>

            {/* Сонголтууд */}
            <Col xs={24} md={8}>
              <Card
                title="Сонголтууд"
                style={{ marginBottom: "20px" }}
                bodyStyle={{ padding: "15px" }}
              >
                <Button
                  icon={<FilePdfOutlined />}
                  style={{
                    marginBottom: "10px",
                    width: "100%",
                    background: "#1890ff",
                    color: "#fff",
                  }}
                  onClick={handleDownloadReport}
                >
                  PDF татах
                </Button>
              </Card>

              <Card
                title="Процессууд"
                style={{ marginBottom: "20px" }}
                bodyStyle={{ padding: "15px" }}
              >
                <Table
                  columns={processColumns}
                  dataSource={processes}
                  pagination={false}
                  bordered
                  size="small"
                  rowKey="id"
                />
              </Card>
            </Col>
          </Row>
        </TabPane>

        <TabPane tab="Процессууд" key="2">
          <StepsComponent processes={processes} handleProcessClick={handleProcessClick} />

          {/* Inventory Table */}
          {inventoryLoading ? (
            <div className="text-center py-8">
              <Spin size="large" />
            </div>
          ) : filteredInventory.length > 0 ? (
            <div className="mb-5 p-5 bg-white rounded-lg shadow-lg">
              <div className="text-xl font-semibold mb-4 text-blue-600">{processes[selectedProcessIndex]?.processName} боломжтой түүхий эдийн мэдээлэл</div>
              <Table
                columns={inventoryColumns(selectedFibers, setSelectedFibers)}
                dataSource={filteredInventory}
                pagination={{ pageSize: 5 }}
                rowKey="id"
                className="rounded-lg"
              />
            </div>
          ) : (
            <div className="mb-5 p-5 bg-white rounded-lg shadow-lg text-center">
              <div className="text-xl font-semibold mb-4 text-red-600">
                Түүхий эд байхгүй байна
              </div>
              {processes[selectedProcessIndex - 1]?.status !== "COMPLETED" ? (
                <div className="text-lg text-gray-700 mb-4">
                  Урьдчилсан процесс дуусаагүй байна. Процессийн хяналт хэсгээс шалгана уу.
                </div>
              ) : (
                <div className="text-lg text-gray-700 mb-4">
                  Түүхий эдийг хүлээн авах шаардлагатай байна. Хүлээн авах товч дарж, түүхий эд нэмнэ үү.
                </div>
              )}
              <Button
                type="primary"
                icon={<PlusOutlined />}

                onClick={handleOpenModal}
              >
                Түүхий эд хүлээн авах
              </Button>
            </div>
          )}

          {/* Process Logs Table */}
          <div className="p-5 bg-white rounded-lg shadow-lg">
            <div className="text-xl font-semibold mb-4 text-blue-600">Процесийн лог</div>
            <Table
              columns={processLogColumns}
              dataSource={processLogs}
              pagination={{ pageSize: 5 }}
              rowKey="id"
              className="rounded-lg"
              rowClassName={(record, index) =>
                index % 2 === 0 ? "bg-gray-100" : "bg-white"
              }
            />
          </div>
        </TabPane>

      </Tabs>

      {activeTab === "2" && processes[selectedProcessIndex]?.status !== "COMPLETED" && canStartProcess(selectedProcessIndex) && (
        <button
          onClick={() => {
            if (processes[selectedProcessIndex]?.status === "IN_PROGRESS") {
              handleEndProcess();
            } else {
              setStartProcessModalVisible(true);
            }
          }}
          className={`fixed bottom-6 right-6 flex items-center justify-center gap-3 px-6 py-3 ${processes[selectedProcessIndex]?.status === "IN_PROGRESS"
            ? "bg-red-500 hover:bg-red-600"
            : "bg-gradient-to-r from-blue-500 via-blue-600 to-blue-700 hover:bg-blue-700"
            } text-white font-semibold text-lg rounded-full shadow-lg hover:shadow-2xl hover:scale-105 transition-all duration-300 ease-in-out`}
        >
          {processes[selectedProcessIndex]?.status === "IN_PROGRESS" ? (
            <>
              <CheckCircleOutlined className="text-2xl" />
              <span>Процесс дуусгах</span>
            </>
          ) : (
            <>
              <PlayCircleOutlined className="text-2xl" />
              <span>Процесс эхлүүлэх</span>
            </>
          )}
        </button>
      )}

      <StartProcessModa
        isModalVisible={startProcessModalVisible}
        onCancel={() => setStartProcessModalVisible(false)}
        // onCancel={handleCancelStartProcess}
        orderDetails={orderDetails}
        selectedProcess={processes[selectedProcessIndex]}
        selectedFibers={selectedFibers}
        setSelectedFibers={setSelectedFibers}
        onSuccess={handleStartProcessSuccess}
      />

      <EndProcessModa
        isModalVisible={endProcessModalVisible}
        onCancel={() => setEndProcessModalVisible(false)}
        orderDetails={orderDetails}
        selectedProcess={processes[selectedProcessIndex]}
        onSuccess={handleEndProcessSuccess}
      />

      <ReceiveItemModal
        isModalVisible={isModalVisible}
        handleCancel={handleCancel}
        handleReceiveItem={handleReceiveItem}
        form={form}
        defaultCustomerName={orderDetails?.customerName}
        defaultColor={orderDetails?.fiberColor}
        defaultFiberType={orderDetails?.fiberType}
      />

    </div>
  );
};

export default OrderDetailsLayout;