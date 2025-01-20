import React from "react";
import { PDFViewer, Document, Page, Text, View, Image, StyleSheet, Font } from "@react-pdf/renderer";

Font.register({
    family: "NotoSans",
    src: "/fonts/NotoSans-Italic-VariableFont_wdth,wght.ttf", 
});

const styles = StyleSheet.create({
    page: {
        padding: 20,
        fontSize: 10,
        fontFamily: "NotoSans", 
    },
    header: {
        flexDirection: "row",
        justifyContent: "space-between",
        alignItems: "center",
        marginBottom: 20,
    },
    logo: {
        width: 50,
        height: 50,
    },
    title: {
        fontSize: 14,
        textAlign: "center",
        marginBottom: 10,
        fontFamily: "NotoSans", 
    },
    section: {
        marginBottom: 10,
    },
    table: {
        display: "table",
        width: "auto",
        borderStyle: "solid",
        borderWidth: 1,
        borderRightWidth: 0,
        borderBottomWidth: 0,
    },
    tableRow: {
        flexDirection: "row",
    },
    tableCol: {
        borderStyle: "solid",
        borderWidth: 1,
        borderLeftWidth: 0,
        borderTopWidth: 0,
        flex: 1,
        textAlign: "center",
        padding: 2,
    },
    tableHeader: {
        backgroundColor: "#f0f0f0",
        fontWeight: "bold",
    },
});

// Table component
const Table = ({ data, columns }) => (
    <View style={styles.table}>
        {/* Table header */}
        <View style={[styles.tableRow, styles.tableHeader]}>
            {columns.map((col, index) => (
                <Text style={styles.tableCol} key={index}>
                    {col}
                </Text>
            ))}
        </View>
        {/* Table body */}
        {data.map((row, rowIndex) => (
            <View style={styles.tableRow} key={rowIndex}>
                {row.map((cell, colIndex) => (
                    <Text style={styles.tableCol} key={colIndex}>
                        {cell}
                    </Text>
                ))}
            </View>
        ))}
    </View>
);

const ReportDocument = () => {
    const yieldData = [
        ["Түүхий эд / Dehaired Cashmere", "298.6", "10.47%", "93.640"],
    ];
    const yieldColumns = [
        "УТГА / DESCRIPTION",
        "БОДИТ ЖИН / Actual (кг/kg)",
        "ЧИЙГ / Moisture (%)",
        "КОНДИЦЫН ЖИН / COMMERCIAL WEIGHT (кг/kg)",
    ];

    const paramData = [
        ["Номер / Count", "метр", "25 (4/25)", "24.5 (4/24.5)"],
        ["Цагаан / White", "-", "-", "-"],
    ];
    const paramColumns = [
        "Д/Д",
        "ДАВХАР ЭЭРМЭЛ / PLIED YARN",
        "НЭГЖ / UNIT",
        "ГЭРЭЭНИЙ ҮЗҮҮЛЭЛТ / PARAMETERS",
        "ШИНЖИЛГЭЭНИЙ ҮР ДҮН / TEST RESULTS",
    ];

    const paymentData = [
        ["1", "Будах / Dyeing", "50", "50,000₮"],
        ["2", "Ээрэх / Spinning", "30", "30,000₮"],
    ];
    const paymentColumns = [
        "Д/Д",
        "УТГА / DESCRIPTION",
        "ЖИН / WEIGHT (кг/kg)",
        "АЖЛЫН ХӨЛС / COMMISSION (төгрөг)",
        "ДҮН / SUBTOTAL (төгрөг)"
    ];

    return (
        <Document>
            <Page size="A4" style={styles.page}>
                {/* Header */}
                <View style={styles.header}>
                    <Image style={styles.logo} src="/logo192.png" /> Move logo to public
                    <Text style={styles.title}>
                        КАШМИР КОНСЕПТ ХХК
                        {"\n"}БҮТЭЭГДЭХҮҮН ХҮЛЭЭЛГЭН ӨГӨХ ХУУДАС
                    </Text>
                </View>

                <View style={styles.section}>
                    <Text>Захиалагч / Customer: Өгөөж Шим</Text>
                    <Text>Хэсэг "lot"-ийн # / Lot #: CA23/04/0404</Text>
                    <Text>Өнгө: 57</Text>
                    <Text>Огноо/ Date: 1/25/2025</Text>
                </View>

                <Text style={{ fontSize: 12, marginTop: 10, fontWeight: "bold" }}>
                    Гарц, жингийн тооцоолол
                </Text>
                <Table data={yieldData} columns={yieldColumns} />

                <Text style={{ fontSize: 12, marginTop: 20, fontWeight: "bold" }}>
                    Чанарын үзүүлэлт
                </Text>
                <Table data={paramData} columns={paramColumns} />

                <Text style={{ fontSize: 12, marginTop: 20, fontWeight: "bold" }}>
                    Төлбөр
                </Text>
                <Table data={paymentData} columns={paymentColumns} />

                <View style={styles.section}>
                    <Text>Төлбөр / Payment: Ган Эрдэнэ</Text>
                    <Text>Хүлээн авагч / Received by: Ган Эрдэнэ</Text>
                </View>

                <View style={styles.footer}>
                    <View style={styles.signature}>
                        <Text>Хүлээлгэн өгсөн / Given by: ...................................................Ган-Эрдэнэ</Text>
                    </View>
                    <View style={styles.signature}>
                        <Text>Хүлээн авсан / Received by: ..................................................Хүлээн авагчийн нэр</Text>
                    </View>
                </View>
            </Page>
        </Document>
    );
};

const ReportTemplate = () => {
    return (
        <PDFViewer width="100%" height="800px">
            <ReportDocument />
        </PDFViewer>
    );
};

export default ReportTemplate;
