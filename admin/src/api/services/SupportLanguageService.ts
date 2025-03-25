/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { paths_1support_language_post_requestBody_content_application_1json_schema } from '../models/paths_1support_language_post_requestBody_content_application_1json_schema';
import type { SupportLanguage } from '../models/SupportLanguage';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class SupportLanguageService {
    /**
     * Create a new support language
     * createSupportLanguage
     * @param requestBody
     * @returns any successful operation
     * @throws ApiError
     */
    public static createSupportLanguage(
        requestBody: {
            language: string;
        },
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: SupportLanguage;
    }> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/support_language',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `Bad request.`,
                404: `The specified resource was not found.`,
            },
        });
    }
    /**
     * Get all support languages
     * getAllSupportLanguages
     * @returns any successful operation
     * @throws ApiError
     */
    public static getAllSupportLanguages(): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Array<SupportLanguage>;
    }> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/support_language',
            errors: {
                400: `Bad request.`,
                404: `The specified resource was not found.`,
            },
        });
    }
    /**
     * Get a support language by ID
     * getSupportLanguageById
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static getSupportLanguageById(
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: SupportLanguage;
    }> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/support_language/{id}',
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
     * Update a support language by ID
     * updateSupportLanguage
     * @param requestBody
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static updateSupportLanguage(
        requestBody: paths_1support_language_post_requestBody_content_application_1json_schema,
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: SupportLanguage;
    }> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/support_language/{id}',
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
     * Delete a support language by ID
     * deleteSupportLanguage
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static deleteSupportLanguage(
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Record<string, any>;
    }> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/support_language/{id}',
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
