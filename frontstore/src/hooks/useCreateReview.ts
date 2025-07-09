import { createReview } from "@/api/review"
import { useMutation } from "@tanstack/react-query"

export const useCreateReview = () => {
  return useMutation({
    mutationFn: createReview,
    onError: (err: any) => console.log(err),
    onSuccess: (data: any) => console.log(data)
  })
}

