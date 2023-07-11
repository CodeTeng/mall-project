import { get } from "../../utils/axios/request";

enum URL {
    getAddress = '/address/getAddress',
    getProvince = '/address/getProvince'
}

export const getAddressApi = async (addressAreaId: any) => get(URL.getAddress, { addressAreaId: addressAreaId })
export const getProvinceApi = async (p?: string) => get(URL.getProvince, undefined)