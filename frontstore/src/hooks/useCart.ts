import { addGameToCart, deleteFromCart, getCart } from "@/api/cart"
import { useMutation, useQuery } from "@tanstack/react-query"

export const useCart = () => {
  return useQuery({
    queryKey: ["cart"],
    queryFn: getCart,
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

