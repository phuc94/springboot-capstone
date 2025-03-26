/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { paths_1playmode_post_requestBody_content_application_1json_schema } from '../models/paths_1playmode_post_requestBody_content_application_1json_schema';
import type { PlayMode } from '../models/PlayMode';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class PlaymodeService {
    /**
     * Create a new play mode
     * createPlayMode
     * @param requestBody
     * @returns any successful operation
     * @throws ApiError
     */
    public static createPlayMode(
        requestBody: {
            name: string;
        },
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: PlayMode;
    }> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/playmode',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `Bad request.`,
                404: `The specified resource was not found.`,
            },
        });
    }
    /**
     * Get all play modes
     * getAllPlayModes
     * @returns any successful operation
     * @throws ApiError
     */
    public static getAllPlayModes(): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Array<PlayMode>;
    }> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/playmode',
            errors: {
                400: `Bad request.`,
                404: `The specified resource was not found.`,
            },
        });
    }
    /**
     * Get a play mode by ID
     * getPlayModeById
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static getPlayModeById(
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: PlayMode;
    }> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/playmode/{id}',
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
     * Update a play mode by ID
     * updatePlayMode
     * @param requestBody
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static updatePlayMode(
        requestBody: paths_1playmode_post_requestBody_content_application_1json_schema,
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: PlayMode;
    }> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/playmode/{id}',
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
     * Delete a play mode by ID
     * deletePlayMode
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static deletePlayMode(
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Record<string, any>;
    }> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/playmode/{id}',
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
