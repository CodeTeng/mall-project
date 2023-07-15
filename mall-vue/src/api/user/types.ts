
/**
 * 登录参数
 */
export interface LoginParams {
    userName: string;
    userPassword: string;
}

export interface UserVO {
    userName?: string;
    userAddress?: string;
    userBirthday: string;
    userGender?: number;
    userHomePlace?: string;
    userNickName: string;
    userProfilePictureSrc?: string;
    userRealname?: string
    userProvince?: string;
}

export interface UserRegisterDTO {
    confirmPassword: string;
    userAddress: number;
    userBirthday: string;
    userGender: number;
    userHomePlace: number;
    userName: string;
    userNickName: string;
    userPassword: string;
}

// 用户更新请求
export interface UpdateUserDTO extends UserVO {
    confirmPassword: string;
    userPassword: string;
}


export interface AddressDTO {
    addressAreaId: string;
    addressName: string;
}

export interface resetFormVO{
    mobile: string;
    userName: string;
}