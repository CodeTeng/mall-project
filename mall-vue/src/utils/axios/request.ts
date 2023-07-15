import { AxiosRequestConfig, AxiosResponse } from 'axios';
import { axiosInstance } from './index'
import { IResponse } from './type';

const request = <T = any>(config: AxiosRequestConfig): Promise<T> => {
    const conf = config;
    return new Promise((resolve) => {
        axiosInstance.request<any, AxiosResponse<IResponse>>(conf).then((res: AxiosResponse<IResponse>) => {
            // resolve(res as unknown as Promise<T>);
            const { data } = res;
            resolve(data as T);
        });
    });
};

/**
 * post请求
 * @param Url 
 * @param Params 
 * @param Data 
 * @returns 
 */
const post = <D = any>(Url: string, Data: D) => {
    const query: AxiosRequestConfig = {
        url: Url,
        method: 'POST',
        data: { ...Data },
        headers: {
            'Content-Type': 'application/json;charset=UTF-8',
            'request-ajax': true,
        }
    }
    return request(query);
}

const postByParams = <P = any>(Url: string, Params: P) => {
    const query: AxiosRequestConfig = {
        url: Url,
        method: 'POST',
        params: { ...Params },
        headers: {
            'Content-Type': 'application/json;charset=UTF-8',
            'request-ajax': true,
        }
    }
    return request(query);
}

/**
 * get请求
 * @param Url 
 * @param Params 
 * @param Data 
 * @returns 
 */
const get = <P = any>(Url: string, Params: P) => {

    const query: AxiosRequestConfig = {
        url: Url,
        method: 'GET',
        params: { ...Params },
        headers: {
            'Content-Type': 'application/json;charset=UTF-8',
            'request-ajax': true,
        }
    }
    return request(query);
}

const getHtml = <P = any>(Url: string, Params: P) => {

    const query: AxiosRequestConfig = {
        url: Url,
        method: 'GET',
        params: { ...Params },
        headers: {
            'Content-Type': 'text/html;charset=UTF-8',
            'request-ajax': true,
        }
    }
    return request(query);
}

/**
 * delete请求
 * @param Url
 * @param Params
 */
const DELETE = <P = any>(Url: string, Params: P) => {

    const query: AxiosRequestConfig = {
        url: Url,
        method: 'delete',
        params: { ...Params },
        headers: {
            'Content-Type': 'application/json;charset=UTF-8',
            'request-ajax': true,
        }
    }
    return request(query);
}



/**
 * 下载资源（图片，视频）
 * @param Url 
 * @param Params 
 * @returns 
 */
const download = <P = any>(Url: string, Params: P) => {
    const query: AxiosRequestConfig = {
        url: Url,
        method: 'get',
        params: { ...Params },
        responseType: 'arraybuffer',
        headers: {
            'Content-Type': 'charset=UTF-8',
            'request-ajax': true
        }
    }
    return axiosInstance(query)
}

/**
 * 上传资源（图片，视频）
 * @param Url 
 * @param File 
 * @returns 
 */
const upload = (Url: string, File: any) => {
    const query: AxiosRequestConfig = {
        url: Url,
        method: 'post',
        data: File,
        headers: {
            'Content-Type': 'multipart/form-data;charset=UTF-8',
            'request-ajax': true,
        }
    }
    return axiosInstance(query)
}


export { get, post, download, upload , DELETE,postByParams,getHtml}