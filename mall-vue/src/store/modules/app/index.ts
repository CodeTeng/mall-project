import { defineStore } from 'pinia';
import { Names } from '../../store-namespace';
import { AppState } from './types';


export const useUserStore = defineStore(Names.App, {
    state: (): AppState => ({
        orderListActiveNumber: 1,
    }),
    getters: {

    },

    actions: {
    }
});