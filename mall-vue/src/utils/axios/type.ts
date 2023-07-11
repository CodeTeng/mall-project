export interface RequestOptions {
    // Whether to process the request result
    // 是否处理请求结果
    isTransformResponse?: boolean;
}

// 返回res.data的interface
export interface IResponse<T = any> {
    code: number | string;
    data: T;
    message: string;
}

/**用户登录 */
export interface ILogin {
    /** 账户名称 */
    username: string;
    /** 账户密码 */
    password: string;
}
