import { useQuery } from "@tanstack/react-query";
import { fetchGames } from "../api/gameApi";

export const useProducts = () => {
  return useQuery({
    queryKey: ["games"],
    queryFn: fetchGames,
  });
};

