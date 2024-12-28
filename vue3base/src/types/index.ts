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
    MOid: number | null;
    Tid: number | null;
    Uid: number | null;
    time: string | null;
    txt: string | null;
    sum: number | null;
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
    Sid: number | null;
    Uid: number | null;
    MOid: number | null;
    time: string | null;
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