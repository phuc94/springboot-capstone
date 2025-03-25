/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export type Cart = {
    id?: number;
    status?: Cart.status;
};
export namespace Cart {
    export enum status {
        ACTIVE = 'ACTIVE',
        CHECKED_OUT = 'CHECKED_OUT',
        EXPIRED = 'EXPIRED',
        ABANDONED = 'ABANDONED',
    }
}

