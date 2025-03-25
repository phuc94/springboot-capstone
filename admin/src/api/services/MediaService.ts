/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { Media } from '../models/Media';
import type { Media_properties_media_type } from '../models/Media_properties_media_type';
import type { paths_1media_post_requestBody_content_application_1json_schema } from '../models/paths_1media_post_requestBody_content_application_1json_schema';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class MediaService {
    /**
     * Create a new media
     * createMedia
     * @param requestBody
     * @returns any successful operation
     * @throws ApiError
     */
    public static createMedia(
        requestBody: {
            media_type: Media_properties_media_type;
            url: string;
            is_primary: boolean;
        },
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Media;
    }> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/media',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `Bad request.`,
                404: `The specified resource was not found.`,
            },
        });
    }
    /**
     * Get all media
     * getAllMedia
     * @returns any successful operation
     * @throws ApiError
     */
    public static getAllMedia(): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Array<Media>;
    }> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/media',
            errors: {
                400: `Bad request.`,
                404: `The specified resource was not found.`,
            },
        });
    }
    /**
     * Get a media by ID
     * getMediaById
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static getMediaById(
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Media;
    }> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/media/{id}',
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
     * Update a media by ID
     * updateMedia
     * @param requestBody
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static updateMedia(
        requestBody: paths_1media_post_requestBody_content_application_1json_schema,
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Media;
    }> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/media/{id}',
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
     * Delete a media by ID
     * deleteMedia
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static deleteMedia(
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Record<string, any>;
    }> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/media/{id}',
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
