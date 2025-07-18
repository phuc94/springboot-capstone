import axiosInstance from "@/vendor/axios";

export const getCart = async () => {
  const { data } = await axiosInstance.get(`/cart`);
  return data;
};

export const addGameToCart = async (gameId: any) => {
  const { data } = await axiosInstance.post(`/cart/${gameId}`);
  return data;
};

export const deleteFromCart = async (gameId: any) => {
  const { data } = await axiosInstance.delete(`/cart/${gameId}`);
  return data;
};

export const updateCart = async (updateItem: UpdateItem) => {
  const { data } = await axiosInstance.post(`/cart/update`, updateItem);
  return data;
};

export interface UpdateItem {
  quantity: number;
  gameId: number
}

