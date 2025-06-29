import { applyCoupon } from "@/api/coupon";
import { notifications } from "@mantine/notifications";
import { useMutation } from "@tanstack/react-query";

export const useApplyCoupon = () => {
  return useMutation({
    mutationFn: applyCoupon,
    onError,
    onSuccess: (data: any) => {
      // if (data.code !== 202) {return;}
      // queryClient.invalidateQueries({ queryKey: ['cart'] })
      // notifications.show({
      //   title: "Điều chỉnh sản phầm thành công!",
      //   message: "",
      // })
    }
  })
}

const onError = () => notifications.show({
  title: 'Áp dụng coupon không thành công!',
  message: 'Mã coupon không đúng hoặc đã hết hạn.',
  color: 'red'
})

