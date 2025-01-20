import { useState, useCallback } from 'react';
import { orderService } from '../Services/order.service';
import { message } from 'antd';

export const useOrders = () => {
    const [orders, setOrders] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    
    const fetchRecentOrders = useCallback(async () => {
        try {
            const data = await orderService.fetchRecentOrders();
            setOrders(data);
        } catch (error) {
            message.error(error.message);
        } finally {
            setIsLoading(false);
        }
    }, []);

    const fetchOrders = useCallback(async () => {
        setIsLoading(true);
        try {
            const data = await orderService.fetchAllOrders();
            setOrders(data);
        } catch (error) {
            message.error(error.message);
        } finally {
            setIsLoading(false);
        }
    }, []);

    const createOrder = async (data) => {
        try {
            const response = await orderService.createOrder(data);
            // setOrders((prevOrders) => {
            //     const newOrders = [...prevOrders, response.data];
            //     return newOrders.sort((a, b) => a.id - b.id);
            // });
            await fetchOrders();
            return response;
        } catch (error) {
            message.error(error.message);
        }
    };

    const deleteOrder = async (id) => {
        try {
            const response = await orderService.deleteOrder(id);
            await fetchOrders();
            return response.data;
        } catch (error) {
            message.error(error.message);
        }
    };

    return {
        orders,
        fetchOrders,
        fetchRecentOrders,
        createOrder,
        deleteOrder,
    };
};