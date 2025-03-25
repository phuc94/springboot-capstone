/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { Game_properties_esrb_rating } from '../models/Game_properties_esrb_rating';
import type { NoPlayer } from '../models/NoPlayer';
import type { paths_1game_get_responses_200_content_application_1json_schema_properties_data_items } from '../models/paths_1game_get_responses_200_content_application_1json_schema_properties_data_items';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class GameFilterService {
    /**
     * Dynamic filter for game
     * filterGames
     * @param genre Game Genre
     * @param avail Game Availability
     * @param minPrice Minimum price
     * @param maxPrice Maximum price
     * @param esrb ESRB rating
     * @param noPlayer Number of players
     * @returns any successful operation
     * @throws ApiError
     */
    public static filterGames(
        genre?: string,
        avail?: string,
        minPrice?: number,
        maxPrice?: number,
        esrb?: Game_properties_esrb_rating,
        noPlayer?: NoPlayer,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Array<paths_1game_get_responses_200_content_application_1json_schema_properties_data_items>;
    }> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/game/filter',
            query: {
                'genre': genre,
                'avail': avail,
                'minPrice': minPrice,
                'maxPrice': maxPrice,
                'esrb': esrb,
                'no_player': noPlayer,
            },
            errors: {
                400: `Bad request.`,
                404: `The specified resource was not found.`,
            },
        });
    }
}
