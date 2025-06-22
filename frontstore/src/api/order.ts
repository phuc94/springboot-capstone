import axiosInstance from "@/vendor/axios";

export const getUserOrder = async (status: string) => {
  const { data } = await axiosInstance.get(`/order/${status}`);
  return data;
};

export const getOrderById = async (id: number) => {
  const { data } = await axiosInstance.get(`/order/${id}`);
  return data;
};

export const cancelOrder = async (orderId: number) => {
  const { data } = await axiosInstance.delete(`/order/${orderId}`);
  return data;
};

