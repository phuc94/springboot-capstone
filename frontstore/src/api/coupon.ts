import axiosInstance from "@/vendor/axios";

export const applyCoupon = async (code: string) => {
  const { data } = await axiosInstance.post(`/coupon/${code}`);
  return data;
};

