
/**
 * 登录参数
 */
// @ts-ignore
interface LoginParams {
    userName: string;
    userPassword: string;
}

interface UserVO {
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

interface UserRegisterDTO {
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
interface UpdateUserDTO extends UserVO {
    confirmPassword: string;
    userPassword: string;
}


interface AddressDTO {
    addressAreaId: string;
    addressName: string;
}