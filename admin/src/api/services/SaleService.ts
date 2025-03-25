/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { paths_1sale_post_requestBody_content_application_1json_schema } from '../models/paths_1sale_post_requestBody_content_application_1json_schema';
import type { Sale } from '../models/Sale';
import type { Sale_properties_status } from '../models/Sale_properties_status';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class SaleService {
    /**
     * Create a new sale
     * createSale
     * @param requestBody
     * @returns any successful operation
     * @throws ApiError
     */
    public static createSale(
        requestBody: {
            start_date: string;
            end_date: string;
            amount: number;
            status: Sale_properties_status;
        },
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Sale;
    }> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/sale',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `Bad request.`,
                404: `The specified resource was not found.`,
            },
        });
    }
    /**
     * Get all sales
     * getAllSales
     * @returns any successful operation
     * @throws ApiError
     */
    public static getAllSales(): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Array<Sale>;
    }> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/sale',
            errors: {
                400: `Bad request.`,
                404: `The specified resource was not found.`,
            },
        });
    }
    /**
     * Get a sale by ID
     * getSaleById
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static getSaleById(
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Sale;
    }> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/sale/{id}',
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
     * Update a sale by ID
     * updateSale
     * @param requestBody
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static updateSale(
        requestBody: paths_1sale_post_requestBody_content_application_1json_schema,
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Sale;
    }> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/sale/{id}',
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
     * Delete a sale by ID
     * deleteSale
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static deleteSale(
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Record<string, any>;
    }> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/sale/{id}',
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
