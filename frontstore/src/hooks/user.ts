import { login, register } from "@/api/authentication"
import { useMutation } from "@tanstack/react-query"
import axios from "axios";

export const useLogin = () => {
  return useMutation({
    mutationFn: login,
    onError: (err: any) => console.log(err),
    onSuccess: (data: any) => {
      localStorage.setItem("token", data.data)
      axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem("token")}`
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

