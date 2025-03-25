/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { NoPlayer } from '../models/NoPlayer';
import type { paths_1no_player_post_requestBody_content_application_1json_schema } from '../models/paths_1no_player_post_requestBody_content_application_1json_schema';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class NoPlayerService {
    /**
     * Create a new no-player type
     * createNoPlayer
     * @param requestBody
     * @returns any successful operation
     * @throws ApiError
     */
    public static createNoPlayer(
        requestBody: {
            no_player: string;
        },
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: NoPlayer;
    }> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/no_player',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `Bad request.`,
                404: `The specified resource was not found.`,
            },
        });
    }
    /**
     * Get all no-player types
     * getAllNoPlayers
     * @returns any successful operation
     * @throws ApiError
     */
    public static getAllNoPlayers(): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Array<NoPlayer>;
    }> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/no_player',
            errors: {
                400: `Bad request.`,
                404: `The specified resource was not found.`,
            },
        });
    }
    /**
     * Get a no-player type by ID
     * getNoPlayerById
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static getNoPlayerById(
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: NoPlayer;
    }> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/no_player/{id}',
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
     * Update a no-player type by ID
     * updateNoPlayer
     * @param requestBody
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static updateNoPlayer(
        requestBody: paths_1no_player_post_requestBody_content_application_1json_schema,
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: NoPlayer;
    }> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/no_player/{id}',
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
     * Delete a no-player type by ID
     * deleteNoPlayer
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static deleteNoPlayer(
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Record<string, any>;
    }> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/no_player/{id}',
            path: {
                'id': id,
            },
            errors: {
                400: `Bad request.`,
                404: `The specified resource was not found.`,
            },
        });
    }
}
