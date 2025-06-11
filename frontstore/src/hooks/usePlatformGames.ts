import { useQuery } from "@tanstack/react-query";
import { fetchPlatformGames } from "@/api/homepage";

export const usePlatformGames = () => {
  return useQuery({
    queryKey: ["platformGames"],
    queryFn: fetchPlatformGames,
  });
};

