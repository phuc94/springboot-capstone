/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { Developer } from '../models/Developer';
import type { paths_1developer_post_requestBody_content_application_1json_schema } from '../models/paths_1developer_post_requestBody_content_application_1json_schema';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class DeveloperService {
    /**
     * Create a new developer
     * createDeveloper
     * @param requestBody
     * @returns any successful operation
     * @throws ApiError
     */
    public static createDeveloper(
        requestBody: {
            name: string;
        },
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Developer;
    }> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/developer',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `Bad request.`,
                404: `The specified resource was not found.`,
            },
        });
    }
    /**
     * Get all developers
     * getAllDevelopers
     * @returns any successful operation
     * @throws ApiError
     */
    public static getAllDevelopers(): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Array<Developer>;
    }> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/developer',
            errors: {
                400: `Bad request.`,
                404: `The specified resource was not found.`,
            },
        });
    }
    /**
     * Get a developer by ID
     * getDeveloperById
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static getDeveloperById(
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Developer;
    }> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/developer/{id}',
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
     * Update a developer by ID
     * updateDeveloper
     * @param requestBody
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static updateDeveloper(
        requestBody: paths_1developer_post_requestBody_content_application_1json_schema,
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Developer;
    }> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/developer/{id}',
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
     * Delete a developer by ID
     * deleteDeveloper
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static deleteDeveloper(
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Record<string, any>;
    }> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/developer/{id}',
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
