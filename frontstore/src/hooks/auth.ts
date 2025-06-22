import { login, register } from "@/api/authentication"
import { useMutation } from "@tanstack/react-query"

export const useLogin = () => {
  return useMutation({
    mutationFn: login,
    onError: (err: any) => console.log(err),
    onSuccess: (data: any) => {
      if (!!data.data.token) {
        localStorage.setItem("token", data.data.token)
      }
    }
  })
}

export const useRegister = () => {
  return useMutation({
    mutationFn: register,
    onError: (err: any) => console.log(err),
    onSuccess: (data: any) => console.log(data)
  })
}

