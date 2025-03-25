/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { Game_properties_description } from '../models/Game_properties_description';
import type { paths_1game_description_post_requestBody_content_application_1json_schema } from '../models/paths_1game_description_post_requestBody_content_application_1json_schema';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class GameDescriptionService {
    /**
     * Create a new game description
     * createGameDescription
     * @param requestBody
     * @returns any successful operation
     * @throws ApiError
     */
    public static createGameDescription(
        requestBody: {
            description: string;
        },
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Game_properties_description;
    }> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/game_description',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `Bad request.`,
                404: `The specified resource was not found.`,
            },
        });
    }
    /**
     * Get all game descriptions
     * getAllGameDescriptions
     * @returns any successful operation
     * @throws ApiError
     */
    public static getAllGameDescriptions(): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Array<Game_properties_description>;
    }> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/game_description',
            errors: {
                400: `Bad request.`,
                404: `The specified resource was not found.`,
            },
        });
    }
    /**
     * Get a game description by ID
     * getGameDescriptionById
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static getGameDescriptionById(
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Game_properties_description;
    }> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/game_description/{id}',
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
     * Update a game description by ID
     * updateGameDescription
     * @param requestBody
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static updateGameDescription(
        requestBody: paths_1game_description_post_requestBody_content_application_1json_schema,
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Game_properties_description;
    }> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/game_description/{id}',
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
     * Delete a game description by ID
     * deleteGameDescription
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static deleteGameDescription(
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Record<string, any>;
    }> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/game_description/{id}',
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
