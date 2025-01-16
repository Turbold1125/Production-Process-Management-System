import React, { useContext, useEffect, useState } from "react";
import { Table, Card } from "antd";

const InputOutputCard = ({
    title, columns, dataSource, pageSize = 5
}) => {
    return (
        <Card
            title={title}
            style={{ marginBottom: "20px" }}
            bodyStyle={{ padding: "15px" }}
        >
            <Table
                columns={columns}
                dataSource={dataSource}
                rowKey="id"
                pagination={{ pageSize }}
                bordered
                style={{ borderRadius: "8px" }}
            />
        </Card>
    )
}

export default InputOutputCard;