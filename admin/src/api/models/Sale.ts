/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { Cart_properties_id } from './Cart_properties_id';
export type Sale = {
    id?: Cart_properties_id;
    start_date?: string;
    end_date?: string;
    amount?: number;
    status?: Sale.status;
};
export namespace Sale {
    export enum status {
        DISABLE = 'DISABLE',
        ACTIVE = 'ACTIVE',
        EXPIRE = 'EXPIRE',
    }
}

