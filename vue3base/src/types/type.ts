/**
 * 评论表
 */
export interface Comment {
    Cid: number | null;
    Uid: number | null;
    MOid: number | null;
    txt: string | null;
    time: string | null;
}

/**
 * 留言表
 */
export interface Message {
    MEid: number | null;
    txt: string | null;
    time: string | null;
    Utoid: number | null;
    Uid: number | null;
}

/**
 * 模组表
 */
export interface Mode {
    MOid: number | null; // 模组id
    Tid: number | null; // 分类id
    Uid: number | null; // 用户id
    name: string | null;// 名称
    file: string | null;// 文件
    download:number | null;// 下载文件
    time: string | null;// 上传时间
    txt: string | null;// 模组描述
    sum: number | null;//收藏量
}

/**
 * 分类表
 */
export interface MType {
    Tid: number | null;
    name: string | null;
}

/**
 * 收藏表
 */
export interface Stow {
    Sid: number | null; // 收藏id
    Uid: number | null; // 用户id
    MOid: number | null; // 模组id
    time: string | null; //收藏时间
}

/**
 * 管理员表
 */
export interface UAdmin {
    Aid: number | null;
    name: string | null;
    phone: string | null;
    mail: string | null;
    pass: string | null;
}

/**
 * 用户表
 */
export interface User {
    Uid: number | null;
    Uimg: string | null;
    Uname: string | null;
    Uphone: string | null;
    Uqq: string | null;
    Umile: string | null;
    Upass: string | null;
}

/**
 * 返回参数
 */
export interface Response {
    statusCode?: number | null;
    code?: string | null;
    list?: any | null;
    message?: string | null;
}