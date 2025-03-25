/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { Cart_properties_id } from './Cart_properties_id';
import type { Developer } from './Developer';
import type { Publisher } from './Publisher';
export type Game = {
    id?: Cart_properties_id;
    is_dlc?: boolean;
    game_dlc_id?: number;
    title?: string;
    price?: number;
    release_date?: string;
    esrb_rating?: Game.esrb_rating;
    is_deleted?: boolean;
    publisher?: Publisher;
    status?: Game.status;
    description?: {
        id?: Cart_properties_id;
        description?: string;
    };
    developer?: Developer;
};
export namespace Game {
    export enum esrb_rating {
        EVERYONE = 'EVERYONE',
        EVERYONE_10 = 'EVERYONE_10',
        TEEN = 'TEEN',
        MATURE = 'MATURE',
        ADULT = 'ADULT',
        PENDING = 'PENDING',
        PENDING_17 = 'PENDING_17',
    }
    export enum status {
        DRAFT = 'DRAFT',
        VISIBLE = 'VISIBLE',
        HIDDEN = 'HIDDEN',
        ARCHIVED = 'ARCHIVED',
    }
}

