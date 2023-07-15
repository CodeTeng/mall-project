import { get, post } from "../../utils/axios/request";
import {LoginParams, resetFormVO, UpdateUserDTO, UserRegisterDTO} from "./types";

enum URL {
    login = '/user/login',
    logout = '/user/logout',
    register = '/user/register',
    info = '/user/getUserVO',
    updateUser = '/user/updateUser',
    findPassword = '/user/findPassword'
}

export const getUserInfo = async () => get(URL.info, undefined);
export const loginUser = async (params: LoginParams) => post(URL.login, params)
export const logoutUser = async () => post(URL.logout, undefined)
export const register = async (params: UserRegisterDTO) => post(URL.register, params)
export const updateUser = async (params: UpdateUserDTO) => post(URL.updateUser, params)
export const findPassword = async (params: resetFormVO) => post(URL.findPassword, params)