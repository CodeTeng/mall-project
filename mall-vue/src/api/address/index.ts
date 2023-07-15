import { get } from "../../utils/axios/request";

enum URL {
    getAddress = '/address/getAddress',
    getProvince = '/address/getProvince'
}

export const getAddressApi = async (addressAreaId: any) => get(URL.getAddress, { addressAreaId: addressAreaId })
export const getProvinceApi = async () => get(URL.getProvince, undefined)
