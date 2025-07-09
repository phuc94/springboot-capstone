import axiosInstance from "@/vendor/axios";

export const createReview = async (review: any) => {
  const { data } = await axiosInstance.post(`/review`, JSON.stringify(review));
  return data;
};

