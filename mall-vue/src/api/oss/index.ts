
import { upload } from "../../utils/axios/request";

enum URL {
    upload = '/file/upload/image',
}
// 上传图片
export const uploadFileApi = async (params: any) => upload(URL.upload, params)