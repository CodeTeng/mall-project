const TokenKey = 'web-token';
const TokenPrefix = 'Bearer';
const UserNameKey = "web-userNameKey"

const isLogin = () => {
    return !!localStorage.getItem(TokenKey);
};


const getToken = () => {
    return localStorage.getItem(TokenKey);
};

const setToken = (token: string) => {
    localStorage.setItem(TokenKey, token);
};

const clearToken = () => {
    localStorage.removeItem(TokenKey);
    localStorage.removeItem(UserNameKey);
};

const getTokenWithPrefix = () => {
    const token = getToken()
    return `${TokenPrefix}-${TokenKey}`
}

const setUserName = (userName: string) => {
    localStorage.setItem(UserNameKey, userName)
}

const getUserName = () => {
    let json = localStorage.getItem(UserNameKey);
    return json;
}

export { TokenPrefix, isLogin, getToken, setToken, clearToken, getTokenWithPrefix, setUserName, getUserName };
