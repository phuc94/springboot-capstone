import { checkout, checkoutFullfill } from "@/api/checkout"
import { useMutation } from "@tanstack/react-query"

export const useCheckout = () => {
  return useMutation({
    mutationFn: checkout,
    onError: (err: any) => console.log(err),
    onSuccess: (data: any) => console.log(data)
  })
}

export const useCheckoutSuccess = () => {
  return useMutation({
    mutationFn: checkoutFullfill,
    onError: (err: any) => console.log(err),
    onSuccess: (data: any) => console.log(data)
  })
}

