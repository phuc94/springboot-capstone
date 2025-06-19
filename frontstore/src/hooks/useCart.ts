import { addGameToCart, deleteFromCart, getCart, updateCart } from "@/api/cart"
import { useMutation, useQuery } from "@tanstack/react-query"

export const useCart = () => {
  return useQuery({
    queryKey: ["cart"],
    queryFn: getCart,
    staleTime: 1000
  })
}

export const useAddToCart = () => {
  return useMutation({
    mutationFn: addGameToCart,
    onError: (err: any) => console.log(err),
    onSuccess: (data: any) => console.log(data)
  })
}

export const useDeleteFromCart = () => {
  return useMutation({
    mutationFn: deleteFromCart,
    onError: (err: any) => console.log(err),
    onSuccess: (data: any) => console.log(data)
  })
}

export const useUpdateCart = () => {
  return useMutation({
    mutationFn: updateCart,
    onError: (err: any) => console.log(err),
    onSuccess: (data: any) => console.log(data)
  })
}

