/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { paths_1genre_get_responses_200_content_application_1json_schema_properties_data_properties_paging } from '../models/paths_1genre_get_responses_200_content_application_1json_schema_properties_data_properties_paging';
import type { paths_1publisher_post_requestBody_content_application_1json_schema } from '../models/paths_1publisher_post_requestBody_content_application_1json_schema';
import type { Publisher } from '../models/Publisher';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class PublisherService {
    /**
     * Create a new publisher
     * createPublisher
     * @param requestBody
     * @returns any successful operation
     * @throws ApiError
     */
    public static createPublisher(
        requestBody: {
            name: string;
        },
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Publisher;
    }> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/publisher',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `Bad request.`,
                404: `Bad request.`,
            },
        });
    }
    /**
     * Get all publishers
     * getAllPublishers
     * @returns any successful operation
     * @throws ApiError
     */
    public static getAllPublishers(): CancelablePromise<{
        code?: number;
        message?: string;
        data?: {
            items?: Array<Publisher>;
            paging?: paths_1genre_get_responses_200_content_application_1json_schema_properties_data_properties_paging;
        };
    }> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/publisher',
            errors: {
                400: `Bad request.`,
                404: `The specified resource was not found.`,
            },
        });
    }
    /**
     * Get a publisher by ID
     * getPublisherById
     * @param id
     * @returns any getPublisherById
     * @throws ApiError
     */
    public static getPublisherById(
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Publisher;
    }> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/publisher/{id}',
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
     * Update a publisher by ID
     * updatePublisher
     * @param requestBody
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static updatePublisher(
        requestBody: paths_1publisher_post_requestBody_content_application_1json_schema,
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Publisher;
    }> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/publisher/{id}',
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
     * Delete a publisher by ID
     * deletePublisher
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static deletePublisher(
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Record<string, any>;
    }> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/publisher/{id}',
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
