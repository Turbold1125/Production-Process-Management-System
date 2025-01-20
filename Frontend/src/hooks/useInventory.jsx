import { useState, useEffect, useCallback } from 'react';
import { inventoryService } from '../Services/Inventory.service';
import { message } from 'antd';

export const useInventory = () => {
    const [inventoryData, setInventoryData] = useState([]);
    const [logData, setLogData] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [searchPerformed, setSearchPerformed] = useState(false);

    const fetchInventoryData = useCallback(async () => {
        setIsLoading(true);
        try {
            const data = await inventoryService.fetchAllinventory();
            setInventoryData(data);
        } catch (error) {
            message.error(error.message);
        } finally {
            setIsLoading(false);
        }
    }, []);

    const fetchInventoryLogs = useCallback(async () => {
        try {
            const data = await inventoryService.fetchInventoryLogs();
            setLogData(data);
        } catch (error) {
            message.error(error.message);
        } finally {
            setIsLoading(false);
        }
    }, []);

    const createInventory = async (data) => {
        try {
            const response = await inventoryService.createInventory(data);
            await fetchInventoryData();
            await fetchInventoryLogs();
            return response;
        } catch (error) {
            message.error(error.message);
        }
    };

    const searchInventory = async (customerName, fiberMaterial) => {
        setIsLoading(true);
        try {
            const data = await inventoryService.searchInventory(customerName, fiberMaterial);
            setInventoryData(data);
            setSearchPerformed(true);
        } catch (error) {
            message.error(error.message);
        } finally {
            setIsLoading(false);
        }
    };

    const filterInventory = async (customer, material, color, fiberType) => {
        setIsLoading(true);
        try {
            const data = await inventoryService.filterInventory(customer, material, color, fiberType);
            setInventoryData(data);
        } catch (error) {
            message.error(error.message);
        } finally {
            setIsLoading(false);
        }
    };
    
    const refreshInventoryData = useCallback(async () => {
        await Promise.all([fetchInventoryData(), fetchInventoryLogs()]);
    }, [fetchInventoryData, fetchInventoryLogs]);

    useEffect(() => {
        refreshInventoryData();
    }, [refreshInventoryData]);

    return {
        inventoryData,
        logData,
        isLoading,
        searchPerformed,
        fetchInventoryData,
        fetchInventoryLogs,
        createInventory,
        searchInventory,
        filterInventory,
        refreshInventoryData,
    };
};