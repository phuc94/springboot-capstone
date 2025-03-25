/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { Cart_properties_id } from './Cart_properties_id';
export type Order = {
    id?: Cart_properties_id;
    user?: {
        id?: Cart_properties_id;
        email?: string;
        name?: string;
        password?: string;
        address?: string;
    };
    order_status?: Order.order_status;
    payment_status?: Order.payment_status;
    payment_method?: Order.payment_method;
    original_amount?: number;
    discount_amount?: number;
    total_amount?: number;
};
export namespace Order {
    export enum order_status {
        PROCESSING = 'PROCESSING',
        PENDING = 'PENDING',
        CANCELLED = 'CANCELLED',
        FAILED = 'FAILED',
    }
    export enum payment_status {
        PENDING = 'PENDING',
        PAID = 'PAID',
        FAIL = 'FAIL',
    }
    export enum payment_method {
        STRAPI = 'STRAPI',
    }
}

