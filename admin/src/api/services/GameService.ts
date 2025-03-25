/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { Cart_properties_id } from '../models/Cart_properties_id';
import type { Game } from '../models/Game';
import type { Game_properties_esrb_rating } from '../models/Game_properties_esrb_rating';
import type { Game_properties_status } from '../models/Game_properties_status';
import type { Publisher } from '../models/Publisher';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class GameService {
    /**
     * Get all games
     * getAllGame
     * @param page
     * @param size
     * @param sort
     * @returns any successful operation
     * @throws ApiError
     */
    public static getAllGame(
        page?: number,
        size?: number,
        sort?: string,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Array<{
            id?: Cart_properties_id;
            title?: string;
            price?: number;
            release_date?: string;
            publisher?: Publisher;
        }>;
    }> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/game',
            query: {
                'page': page,
                'size': size,
                'sort': sort,
            },
            errors: {
                400: `Bad request.`,
                404: `The specified resource was not found.`,
            },
        });
    }
    /**
     * Create new Game
     * addGame
     * @param requestBody Create a new Game
     * @returns any Successful
     * @throws ApiError
     */
    public static addGame(
        requestBody: {
            is_dlc: boolean;
            game_dlc_id?: number;
            title: string;
            price?: number;
            realease_date?: string;
            esrb_rating?: Game_properties_esrb_rating;
            is_deleted?: boolean;
            publisher_id?: number;
            status: Game_properties_status;
            description_id?: number;
            developer_id?: number;
        },
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Array<Game>;
    }> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/game',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `Bad request.`,
                404: `The specified resource was not found.`,
            },
        });
    }
    /**
     * Get Game by Id
     * getGameById
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static getGameById(
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Game;
    }> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/game/{game_id}',
            path: {
                'id': id,
            },
            errors: {
                400: `Bad request.`,
                404: `The specified resource was not found.`,
            },
        });
    }
    /**
     * Update a Game
     * updateGame
     * @param requestBody Update a Game
     * @param id
     * @returns any Successful
     * @throws ApiError
     */
    public static updateGame(
        requestBody: Game,
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Game;
    }> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/game/{game_id}',
            path: {
                'id': id,
            },
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `Bad request.`,
                404: `The specified resource was not found.`,
            },
        });
    }
    /**
     * Deletes a Game
     * deleteGame
     * @param gameId Game id to be delete
     * @returns any successful operation
     * @throws ApiError
     */
    public static deleteGame(
        gameId: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Record<string, any>;
    }> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/game/{game_id}',
            path: {
                'game_id': gameId,
            },
            errors: {
                400: `Bad request.`,
                404: `The specified resource was not found.`,
            },
        });
    }
}
