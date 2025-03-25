/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { Coupon } from '../models/Coupon';
import type { Coupon_properties_status } from '../models/Coupon_properties_status';
import type { paths_1coupon_post_requestBody_content_application_1json_schema } from '../models/paths_1coupon_post_requestBody_content_application_1json_schema';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class CouponService {
    /**
     * Create a new coupon
     * createCoupon
     * @param requestBody
     * @returns any successful operation
     * @throws ApiError
     */
    public static createCoupon(
        requestBody: {
            code: string;
            discount_amount: number;
            usage_limit: number;
            used_count: number;
            start_date: string;
            end_date: string;
            status: Coupon_properties_status;
            type: string;
        },
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Coupon;
    }> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/coupon',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `Bad request.`,
                404: `The specified resource was not found.`,
            },
        });
    }
    /**
     * Get all coupons
     * getAllCoupons
     * @returns any successful operation
     * @throws ApiError
     */
    public static getAllCoupons(): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Array<Coupon>;
    }> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/coupon',
            errors: {
                400: `Bad request.`,
                404: `The specified resource was not found.`,
            },
        });
    }
    /**
     * Get a coupon by ID
     * getCouponById
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static getCouponById(
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Coupon;
    }> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/coupon/{id}',
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
     * Update a coupon by ID
     * updateCoupon
     * @param requestBody
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static updateCoupon(
        requestBody: paths_1coupon_post_requestBody_content_application_1json_schema,
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Coupon;
    }> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/coupon/{id}',
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
     * Delete a coupon by ID
     * deleteCoupon
     * @param id
     * @returns any successful operation
     * @throws ApiError
     */
    public static deleteCoupon(
        id?: number,
    ): CancelablePromise<{
        code?: number;
        message?: string;
        data?: Record<string, any>;
    }> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/coupon/{id}',
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
