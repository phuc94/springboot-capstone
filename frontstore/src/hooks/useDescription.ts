import { fetchDescriptionById } from "@/api/description";
import { useQuery } from "@tanstack/react-query";

export const useDescription = (id: number) => {
  return useQuery({
    queryKey: ["game_description"],
    queryFn: () => fetchDescriptionById(id),
  });
};

