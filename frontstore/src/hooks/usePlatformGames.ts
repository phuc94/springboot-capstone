import { useQuery } from "@tanstack/react-query";
import { fetchPlatformGames } from "@/api/homepage";
import { fetchPlatformGameList } from "@/api/gameApi";

export const usePlatformGames = () => {
  return useQuery({
    queryKey: ["platformGames"],
    queryFn: fetchPlatformGames,
  });
};

export const usePlatformGameList = (platformName: string) => {
  return useQuery({
    queryKey: ["platformGameList", platformName],
    queryFn: () => fetchPlatformGameList(platformName),
  });
};