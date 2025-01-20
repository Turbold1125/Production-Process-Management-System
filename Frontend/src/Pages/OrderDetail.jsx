import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { Card, Button, Row, Col, Table, Spin, Alert, message, Tabs, Tag } from "antd";
import { CheckCircleOutlined, FilePdfOutlined, PlayCircleOutlined, PlusOutlined } from "@ant-design/icons";
import useOrderDetails from "../hooks/useOrderDetailss";
import { inventoryColumns, processColumns, processIOColumns, processLogColumns } from "../Components/OrderDetails/Columns";
import StepsComponent from "../Components/OrderDetails/Steps";
import ReceiveItemModal from "../Components/Modals/ReceiveItemModal";
import Header from "../Components/OrderDetails/Header";
import OrderInfo from "../Components/OrderDetails/OrderInfo";
import StartProcessModal from "../Components/Modals/StartProcessModal";
import EndProcessModal from "../Components/Modals/EndProcessModal";
import InputOutputCard from "../Components/OrderDetails/InputOutputCard";
import { processService } from "../Services/process.service";
import { reportService } from "../Services/report.service";

const { TabPane } = Tabs;

const getStatusTag = (status) => {
  const colorMap = {
    COMPLETED: "green",
    IN_PROGRESS: "blue",
    PENDING: "orange",
  };
  return <Tag color={colorMap[status] || "default"}>{status}</Tag>;
};

const OrderDetailsLayout = () => {
  const { id: orderId } = useParams();

  const navigate = useNavigate();

  const {
    orderDetails,
    processes: initialProcesses,
    loading,
    error,
    processLogs,
    fetchProcessLogs,
    fetchProcesses,
    fetchOrderById,
    processInputs,
    processOutputs,
    fetchProcessInputs,
    fetchProcessOutputs
  } = useOrderDetails(orderId);

  const [processes, setProcesses] = useState([]);
  const [selectedProcessIndex, setSelectedProcessIndex] = useState(0);
  const [selectedFibers, setSelectedFibers] = useState([]);
  const [startProcessModalVisible, setStartProcessModalVisible] = useState(false);
  const [filteredInventory, setFilteredInventory] = useState([]);
  const [activeTab, setActiveTab] = useState("1");

  const [endProcessModalVisible, setEndProcessModalVisible] = useState(false);

  const [isReceiveModalVisible, setIsReceiveModalVisible] = useState(false);
  const [selectedInputMaterial, setSelectedInputMaterial] = useState(null);

  useEffect(() => {
    setProcesses(initialProcesses);
  }, [initialProcesses]);

  useEffect(() => {
    const fetchRequiredMaterials = async () => {
      const selectedProcess = processes[selectedProcessIndex];
      if (!selectedProcess) return;

      try {
        const response = await processService.requiredMaterial(
          selectedProcess.processName,
          orderDetails?.customerName
        );
        setFilteredInventory(response);
      } catch {
        message.error("Шаардлагатай материалыг татаж чадсангүй.");
      }
    };

    fetchRequiredMaterials();
  }, [selectedProcessIndex, processes, orderDetails]);

  const handleProcessClick = (index) => {
    setSelectedProcessIndex(index);

    const selectedProcess = processes[index];
    const matchingFactoryProcess = orderDetails?.factoryProcesses.find(
      (factoryProcess) => factoryProcess.name === selectedProcess?.processName
    );


    const selectedInputMaterial = matchingFactoryProcess?.inputs || null;
    
    setSelectedInputMaterial(selectedInputMaterial)
    console.log("OrderDetails:::", orderDetails)
    console.log("selectedProcessIndex:::", processes[selectedProcessIndex])
    console.log("SelectedProcessMaterial", selectedInputMaterial)
  };

  /* ---------------------------------------- */

  const handleProcessSuccess = (setModalVisible) => {
    setModalVisible(false);
    fetchProcesses();
    fetchProcessLogs();
    fetchProcessInputs();
    fetchProcessOutputs();
    fetchOrderById(orderId);
  }

  const handleStartProcessSuccess = () => {
    handleProcessSuccess(setStartProcessModalVisible);
  };

  const handleEndProcessSuccess = () => {
    handleProcessSuccess(setEndProcessModalVisible);
  };

  const handleOpenModal = () => {
    setIsReceiveModalVisible(true);
  };

  const handleReceiveItemSuccess = () => {
    setIsReceiveModalVisible(false)
    fetchProcesses();
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
      await reportService.downloadOrderReport(orderId);
      message.success("PDF файл амжилттай татагдлаа.");
    } catch (error) {
      message.error(error.message);
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
          {filteredInventory.length > 0 ? (
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

      <StartProcessModal
        isModalVisible={startProcessModalVisible}
        onCancel={() => setStartProcessModalVisible(false)}
        orderDetails={orderDetails}
        selectedProcess={processes[selectedProcessIndex]}
        selectedFibers={selectedFibers}
        setSelectedFibers={setSelectedFibers}
        onSuccess={handleStartProcessSuccess}
      />

      <EndProcessModal
        isModalVisible={endProcessModalVisible}
        onCancel={() => setEndProcessModalVisible(false)}
        orderDetails={orderDetails}
        selectedProcess={processes[selectedProcessIndex]}
        onSuccess={handleEndProcessSuccess}
      />

      <ReceiveItemModal
        isModalVisible={isReceiveModalVisible}
        handleCancel={() => setIsReceiveModalVisible(false)}
        setRefresh={handleReceiveItemSuccess}
        defaultCustomerName={orderDetails?.customerName}
        defaultFiberColor={orderDetails?.fiberColor}
        defaultFiberType={orderDetails?.fiberType}
        defaultMaterial={selectedInputMaterial}
      />

    </div>
  );
};

export default OrderDetailsLayout;