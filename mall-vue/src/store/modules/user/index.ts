import { defineStore } from 'pinia'
import { Names } from '../../store-namespace';
import { UserState } from './types';
import { getUserInfo, loginUser, logoutUser } from '../../../api/user';
import { setToken, clearToken, setUserName } from '../../../utils/auth';

export const useUserStore = defineStore(Names.User, {
    state: (): UserState => ({
        userName: undefined,
        userAddress: undefined,
        userBirthday: undefined,
        userGender: undefined,
        userHomePlace: undefined,
        userNickName: undefined,
        userProfilePictureSrc: undefined,
        userRealname: undefined,
    }),
    getters: {
        // 用户信息描述
        userProfile(state: UserState): UserState {
            return { ...state };
        },
    },
    actions: {
        // 设置用户的信息
        setInfo(partial: Partial<UserState>) {
            this.$patch(partial);
        },

        // 重置用户信息
        resetInfo() {
            this.$reset();
        },

        // 获取用户信息
        async getInfo() {
            const result = await getUserInfo();
            this.setInfo(result.data);
            setUserName(result.data.userName)
            return result;
        },

        // 异步登录并存储token
        async login(loginForm: LoginParams) {
            const result = await loginUser(loginForm);
            console.log(result);
            const token = result?.data;
            if (token) {
                setToken(token);
            }
            return result;
        },

        // 异步退出
        async logout() {
            await logoutUser();
            this.resetInfo();
            clearToken();
            // 路由表重置
            location.replace("/mall");
        },
    }
});