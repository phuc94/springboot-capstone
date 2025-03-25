/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { Genre } from '../models/Genre';
import type { paths_1genre_post_requestBody_content_application_1json_schema } from '../models/paths_1genre_post_requestBody_content_application_1json_schema';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class GenreService {
    /**
     * Create a new Genre
     * @param requestBody
     * @returns any Genre created successfully
     * @throws ApiError
     */
    public static createGenre(
        requestBody: {
            genre: string;
        },
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Genre;
    }> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/genre',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `Bad request.`,
            },
        });
    }
    /**
     * Get all Genres
     * @returns any List of Genres
     * @throws ApiError
     */
    public static getAllGenres(): CancelablePromise<{
        code?: number;
        message?: string;
        data?: {
            items?: Array<Genre>;
            paging?: {
                page?: number;
                size?: number;
                total_records?: number;
                total_pages?: number;
            };
        };
    }> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/genre',
            errors: {
                400: `Bad request.`,
            },
        });
    }
    /**
     * Get a Genre by ID
     * @param id
     * @returns any Genre details
     * @throws ApiError
     */
    public static getGenreById(
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Genre;
    }> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/genre/{id}',
            path: {
                'id': id,
            },
            errors: {
                404: `The specified resource was not found.`,
            },
        });
    }
    /**
     * Update a Genre by ID
     * @param requestBody
     * @param id
     * @returns any Genre updated successfully
     * @throws ApiError
     */
    public static updateGenre(
        requestBody: paths_1genre_post_requestBody_content_application_1json_schema,
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Genre;
    }> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/genre/{id}',
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
     * Delete a Genre by ID
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static deleteGenre(
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Record<string, any>;
    }> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/genre/{id}',
            path: {
                'id': id,
            },
            errors: {
                404: `The specified resource was not found.`,
            },
        });
    }
}
