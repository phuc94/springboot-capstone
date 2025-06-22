import { cancelOrder, getOrderById, getUserOrder } from "@/api/order";
import { queryClient } from "@/routes/__root";
import { notifications } from "@mantine/notifications";
import { useMutation, useQuery } from "@tanstack/react-query";

export const useOrderPending = () => {
  return useQuery({
    queryKey: ["order", "pending"],
    queryFn: () => getUserOrder('pending'),
  });
};

export const useOrderSuccess = () => {
  return useQuery({
    queryKey: ["order", "success"],
    queryFn: () => getUserOrder('success'),
  });
};

export const useOrderCanceled = () => {
  return useQuery({
    queryKey: ["order", "canceled"],
    queryFn: () => getUserOrder('canceled'),
  });
};

export const useOrderById = (id: number) => {
  return useQuery({
    queryKey: ["order", id],
    queryFn: () => getOrderById(id),
  });
};

export const useCancelOrder = () => {
  return useMutation({
    mutationFn: cancelOrder,
    onError,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['order', 'canceled'] });
      queryClient.invalidateQueries({ queryKey: ['order', 'pending'] });
    }
  });
};

const onError = () => notifications.show({
  title: 'Có lỗi xảy ra!',
  message: 'Xin mời thử lại sau giây lát.',
})
