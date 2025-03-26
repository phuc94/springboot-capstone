/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { Cart_properties_id } from './Cart_properties_id';
export type Coupon = {
    id?: Cart_properties_id;
    code?: string;
    discount_amount?: number;
    usage_limit?: number;
    used_count?: number;
    start_date?: string;
    end_date?: string;
    status?: Coupon.status;
    type?: string;
};
export namespace Coupon {
    export enum status {
        DISABLE = 'DISABLE',
        ACTIVE = 'ACTIVE',
        EXPIRE = 'EXPIRE',
    }
}

