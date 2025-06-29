import { addGameToCart, deleteFromCart, getCart, updateCart } from "@/api/cart"
import { queryClient } from "@/routes/__root"
import { useAuthStore } from "@/store/useAuthStore"
import { notifications } from "@mantine/notifications"
import { useMutation, useQuery } from "@tanstack/react-query"

export const useCart = () => {
  const isAuthenticated = useAuthStore(state => state.isAuthenticated)
  return useQuery({
    queryKey: ["cart"],
    queryFn: getCart,
    staleTime: 1000,
    enabled: isAuthenticated
  })
}

export const useAddToCart = () => {
  return useMutation({
    mutationFn: addGameToCart,
    onError,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['cart'] })
    }
  })
}

export const useDeleteFromCart = () => {
  return useMutation({
    mutationFn: deleteFromCart,
    onError,
    onSuccess: (data: any) => {
      if (data.code !== 202) {return;}
      queryClient.invalidateQueries({ queryKey: ['cart'] })
      notifications.show({
        title: "❌ Xóa sản phầm thành công!",
        message: "",
      })
    }
  })
}

export const useUpdateCart = () => {
  return useMutation({
    mutationFn: updateCart,
    onError,
    onSuccess: (data: any) => {
      if (data.code !== 202) {return;}
      queryClient.invalidateQueries({ queryKey: ['cart'] })
      notifications.show({
        title: "Điều chỉnh sản phầm thành công!",
        message: "",
      })
    }
  })
}

const onError = () => notifications.show({
  title: 'Có lỗi xảy ra!',
  message: 'Xin mời thử lại sau giây lát.',
})

