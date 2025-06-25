import axiosInstance from "@/vendor/axios";

export const checkout = async (receiverDetail: any) => {
  const { data } = await axiosInstance.post(`/checkout`, receiverDetail);
  return data;
};

export const checkoutFullfill = async (sessionId: string) => {
  const { data } = await axiosInstance.post(`/checkout/fullfill?sessionId=${sessionId}`);
  return data;
};

