/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { Cart_properties_id } from './Cart_properties_id';
export type Media = {
    id?: Cart_properties_id;
    media_type?: Media.media_type;
    url?: string;
    is_primary?: boolean;
};
export namespace Media {
    export enum media_type {
        IMAGE = 'IMAGE',
        VIDEO = 'VIDEO',
    }
}

