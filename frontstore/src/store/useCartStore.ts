import { create } from "zustand";
import { persist } from "zustand/middleware";

interface CartState {
  items: { id: number; name: string; quantity: number }[];
  originalPrice: number;
  saleAmount: number;
  subTotalAmount: number;
  discountAmount: number;
  totalAmount: number;
  // addItem: (item: { id: number; name: string }) => void;
  // removeItem: (id: number) => void;
  setStore: (data: any) => void;
}

export const useCartStore = create<CartState>((set:any) => ({
  items: [],
  originalPrice: 0,
  saleAmount: 0,
  subTotalAmount: 0,
  discountAmount: 0,
  totalAmount: 0,
  setStore: (data: any) => set((_:any) => (data))
}));

export interface PaymentState {
  sessionId: string;
  url: string;
  status: string,
  init: (data: {sessionId: string; url: string, status: string}) => void
}

export const usePaymentStore = create<PaymentState>()(
  persist(
    (set) => ({
      sessionId: "",
      url: "",
      status: "",
      init: (data) => {set(data);}
    }),
    {
      name: "payment",
    }
  )
);

