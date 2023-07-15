import { getHtml } from "../../utils/axios/request";
import {PayType} from "./type";

enum URL {
    alipay = '/pay/alipay',
}

export const alipayApi = async (params:PayType) =>getHtml(URL.alipay,params);
