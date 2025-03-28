import { create } from "zustand";

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

