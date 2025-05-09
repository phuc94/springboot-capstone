import { fetchDescriptions } from "@/api/description";
import { useQuery } from "@tanstack/react-query";

export const useDescription = () => {
  return useQuery({
    queryKey: ["game_description"],
    queryFn: fetchDescriptions,
  });
};

