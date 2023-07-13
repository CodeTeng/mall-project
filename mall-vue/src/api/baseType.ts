
export interface pageType {
    // 	当前页号
    current: number;
    // 页面大小 默认10页
    pageSize: number;
    // 	排序字段
    sortField?: string;
    // 	排序规则 默认降序  降desc  升asc
    sortOrder?: string;
}
