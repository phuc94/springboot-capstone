import { useQuery } from "@tanstack/react-query";
import { fetchGameDetail } from "../api/gameApi";

export const useGameDetail = (id: number) => {
  return useQuery({
    queryKey: ["gameDetail"],
    queryFn: () => fetchGameDetail(id),
  });
};

