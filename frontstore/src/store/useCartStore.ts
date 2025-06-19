import { create } from "zustand";
import { persist } from "zustand/middleware";

interface CartState {
  items: { id: number; name: string; quantity: number }[];
  addItem: (item: { id: number; name: string }) => void;
  removeItem: (id: number) => void;
}

export const useCartStore = create<CartState>((set:any) => ({
  items: [],
  addItem: (item:any) =>
    set((state:any) => {
      const existing = state.items.find((i:any) => i.id === item.id);
      if (existing) {
        return {
          items: state.items.map((i:any) =>
            i.id === item.id ? { ...i, quantity: i.quantity + 1 } : i
          ),
        };
      }
      return { items: [...state.items, { ...item, quantity: 1 }] };
    }),
  removeItem: (id:any) =>
    set((state:any) => ({ items: state.items.filter((item:any) => item.id !== id) })),
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

