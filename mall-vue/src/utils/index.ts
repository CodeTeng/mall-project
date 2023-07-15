
/**
 * 绑定图片
 *
 * @param imgName
 */
export const bindImg = (imgName?: string): string => {
    let imgUrl = "http://139.9.51.117:9000/image/mall/img/" + imgName;
    return imgUrl;
}

/**
 *
 *
 * @param imgName
 */
export const bindWebsite = (imgName?: string): string => {
    let imgUrl = "http://139.9.51.117:9000/image/mall/website/" + imgName;
    return imgUrl;
}
